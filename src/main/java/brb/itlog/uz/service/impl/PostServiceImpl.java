package brb.itlog.uz.service.impl;

import brb.itlog.uz.exception.AppBadException;
import brb.itlog.uz.model.entity.post.Posts;
import brb.itlog.uz.model.entity.post.Tags;
import brb.itlog.uz.model.mapper.PostMapper;
import brb.itlog.uz.model.dto.post.request.AuthorDTO;
import brb.itlog.uz.model.dto.post.request.CreatePostRequestDTO;
import brb.itlog.uz.model.dto.post.request.PostDTO;
import brb.itlog.uz.model.dto.post.request.TagDTO;
import brb.itlog.uz.model.entity.post.Authors;
import brb.itlog.uz.pagination.ActivePostsDTO;
import brb.itlog.uz.pagination.MetaDTO;
import brb.itlog.uz.repository.AuthorRepository;
import brb.itlog.uz.repository.PostRepository;
import brb.itlog.uz.repository.TagRepository;
import brb.itlog.uz.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final TagRepository tagRepository;

    @Override
    public String createPost(CreatePostRequestDTO request) {

        List<Tags> tagsToSave = new ArrayList<>();
        List<Authors> authorsToSave = new ArrayList<>();

        for (PostDTO postDTO : request.getPosts()) {

            for (TagDTO tagDTO : postDTO.getTagsDetailed()) {
                Tags tags = tagRepository.findByName(tagDTO.getName())
                        .orElse(new Tags(tagDTO.getName(), tagDTO.getDescription()));
                tagsToSave.add(tags);
            }

            for (AuthorDTO authorDTO : postDTO.getAuthorsDetailed()) {
                Authors authors = authorRepository.findById(Long.parseLong(authorDTO.getId()))
                        .orElseThrow(() -> new RuntimeException("Author not found: " + authorDTO.getId()));
                authorsToSave.add(authors);
            }
        }

        tagRepository.saveAll(tagsToSave);
        authorRepository.saveAll(authorsToSave);

        List<Posts> postsToSave = new ArrayList<>();

        for (PostDTO postDTO : request.getPosts()) {
            Posts posts = new Posts();
            posts.setTitle(postDTO.getTitle());
            posts.setStatus(postDTO.getStatus());
            posts.setLexical(postDTO.getLexical());
            posts.setHtml(postDTO.getHtml());

            List<Tags> postTags = new ArrayList<>();
            for (TagDTO tagDTO : postDTO.getTagsDetailed()) {
                Tags tags = tagRepository.findByName(tagDTO.getName())
                        .orElseThrow(() -> new RuntimeException("Tag not found: " + tagDTO.getName()));
                postTags.add(tags);
            }
            posts.setTags(postTags);

            List<Authors> postAuthors = new ArrayList<>();
            for (AuthorDTO authorDTO : postDTO.getAuthorsDetailed()) {
                Authors authors = authorRepository.findById(Long.parseLong(authorDTO.getId()))
                        .orElseThrow(() -> new RuntimeException("Author not found: " + authorDTO.getId()));
                postAuthors.add(authors);
            }
            posts.setAuthors(postAuthors);

            postsToSave.add(posts);
        }

        postRepository.saveAll(postsToSave);

        log.info("Post created");

        return "Post created";
    }

    @Override
    public PostDTO copyPost(Long postId) {
        Posts originalPosts = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        Posts copiedPosts = new Posts();
        copiedPosts.setTitle(originalPosts.getTitle() + " (Copy)");
        copiedPosts.setSlug(generateUniqueSlug(originalPosts.getSlug()));
        copiedPosts.setStatus("draft");
        copiedPosts.setLexical(originalPosts.getLexical());
        copiedPosts.setHtml(originalPosts.getHtml());
        copiedPosts.setTags(originalPosts.getTags());
        copiedPosts.setAuthors(originalPosts.getAuthors());

        return convertToPostDTO(copiedPosts);
    }

    private String generateUniqueSlug(String originalSlug) {
        String newSlug = originalSlug + "-copy";
        int count = 1;
        while (postRepository.existsBySlug(newSlug)) {
            newSlug = originalSlug + "-copy-" + count++;
        }
        return newSlug;
    }

    private PostDTO convertToPostDTO(Posts posts) {
        PostDTO dto = new PostDTO();
        dto.setTitle(posts.getTitle());
        dto.setSlug(posts.getSlug());
        dto.setStatus(posts.getStatus());
        dto.setLexical(posts.getLexical());
        dto.setHtml(posts.getHtml());

        List<String> authorIds = posts.getAuthors().stream()
                .map(a -> String.valueOf(a.getId()))
                .collect(Collectors.toList());
        dto.setAuthors(authorIds);

        List<AuthorDTO> detailedAuthors = posts.getAuthors().stream()
                .map(a -> {
                    AuthorDTO adto = new AuthorDTO();
                    adto.setId(String.valueOf(a.getId()));
                    return adto;
                })
                .collect(Collectors.toList());
        dto.setAuthorsDetailed(detailedAuthors);

        List<TagDTO> tagDTOs = posts.getTags().stream()
                .map(tag -> {
                    TagDTO t = new TagDTO();
                    t.setName(tag.getName());
                    t.setDescription(tag.getDescription());
                    return t;
                }).collect(Collectors.toList());
        dto.setTagsDetailed(tagDTOs);

        return dto;
    }

    @Override
    public String updateTitle(Long postId, String newTitle) {
        Posts posts = postRepository.findById(postId)
                .orElseThrow(() -> {
                    log.error("Post not found with ID: {}", postId);
                    return new AppBadException("Post not found with ID: " + postId);
                });
        posts.setTitle(newTitle);
        Posts savedPosts = postRepository.save(posts);
        log.info("Post Title successfuly Updated with title : {}", savedPosts.getTitle());
        return "Post Title updated";
    }

    @Override
    public String updatePostStatusPublished(Long postId, String status) {
        Posts posts = postRepository.findById(postId)
                .orElseThrow(() -> {
                    log.error("Post not found with ID: {}", postId);
                    return new AppBadException("Post not found with ID: " + postId);
                });
        posts.setStatus(status);
        Posts savedPosts = postRepository.save(posts);
        log.info("Post Status successfuly Updated with status : {}", savedPosts.getStatus());
        return "Post Status updated with status : " + savedPosts.getStatus();
    }

    @Override
    public String updatePostStatusScheduled(Long postId, String status, String publishedAt) {
        Posts posts = postRepository.findById(postId)
                .orElseThrow(() -> {
                    log.error("Post not found with ID: {}", postId);
                    return new AppBadException("Post not found with ID: " + postId);
                });
        posts.setStatus(status);
        LocalDate localDate = LocalDate.parse(publishedAt);
        posts.setPublishedAt(localDate.atStartOfDay());
        Posts savedPosts = postRepository.save(posts);
        log.info("Post Status Scheduled Successfully Updated with status : {}", savedPosts.getStatus());
        return "Post Status Updated with status : " + savedPosts.getStatus() + " and publishedAt : " + publishedAt;
    }

    @Override
    public String deletePost(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Post not found with ID: {}", id);
                    return new AppBadException("Post not found with ID: " + id);
                });
        postRepository.deleteById(id);
        log.info("Post deleted successfully");
        return "Post deleted successfully";
    }

    @Override
    public MetaDTO getAllPosts(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Posts> postsPage = postRepository.findAll(pageable);

        List<ActivePostsDTO> activePostsDTOS = postsPage.getContent().stream()
                .map(this::toFullInfo)
                .toList();

        MetaDTO metaDTO = MetaDTO.builder()
                .pageNo(postsPage.getNumber())
                .pageSize(postsPage.getSize())
                .totalElements(postsPage.getTotalElements())
                .totalPages(postsPage.getTotalPages())
                .last(postsPage.isLast())
                .content(activePostsDTOS)
                .build();

        return metaDTO;
    }

    private ActivePostsDTO toFullInfo(Posts posts) {
        List<TagDTO> tagDTOs = posts.getTags().stream()
                .map(tag -> {
                    TagDTO dto = new TagDTO();
                    dto.setName(tag.getName());
                    dto.setDescription(tag.getDescription());
                    return dto;
                }).toList();

        List<AuthorDTO> authorDTOs = posts.getAuthors().stream()
                .map(author -> {
                    AuthorDTO dto = new AuthorDTO();
                    dto.setId(String.valueOf(author.getId()));
                    return dto;
                }).toList();

        return new ActivePostsDTO(
                posts.getId(),
                posts.getTitle(),
                posts.getLexical(),
                posts.getHtml(),
                posts.getStatus(),
                posts.getSlug(),
                posts.getPublishedAt(),
                tagDTOs,
                authorDTOs
        );
    }

}
