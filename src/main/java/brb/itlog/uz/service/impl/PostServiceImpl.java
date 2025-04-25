package brb.itlog.uz.service.impl;

import brb.itlog.uz.exception.AppBadException;
import brb.itlog.uz.model.entity.post.Author;
import brb.itlog.uz.model.entity.post.Post;
import brb.itlog.uz.model.entity.post.Tag;
import brb.itlog.uz.model.mapper.PostMapper;
import brb.itlog.uz.model.dto.post.request.AuthorDTO;
import brb.itlog.uz.model.dto.post.request.CreatePostRequestDTO;
import brb.itlog.uz.model.dto.post.request.PostDTO;
import brb.itlog.uz.model.dto.post.request.TagDTO;
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

        List<Tag> tagToSave = new ArrayList<>();
        List<Author> authorToSave = new ArrayList<>();

        for (PostDTO postDTO : request.getPosts()) {

            for (TagDTO tagDTO : postDTO.getTagsDetailed()) {
                Tag tag = tagRepository.findByName(tagDTO.getName())
                        .orElse(new Tag(tagDTO.getName(), tagDTO.getDescription()));
                tagToSave.add(tag);
            }

            for (AuthorDTO authorDTO : postDTO.getAuthorsDetailed()) {
                Author author = authorRepository.findById(Long.parseLong(authorDTO.getId()))
                        .orElseThrow(() -> new RuntimeException("Author not found: " + authorDTO.getId()));
                authorToSave.add(author);
            }
        }

        tagRepository.saveAll(tagToSave);
        authorRepository.saveAll(authorToSave);

        List<Post> postToSave = new ArrayList<>();

        for (PostDTO postDTO : request.getPosts()) {
            Post post = new Post();
            post.setTitle(postDTO.getTitle());
            post.setStatus(postDTO.getStatus());
            post.setLexical(postDTO.getLexical());
            post.setHtml(postDTO.getHtml());

            List<Tag> postTags = new ArrayList<>();
            for (TagDTO tagDTO : postDTO.getTagsDetailed()) {
                Tag tag = tagRepository.findByName(tagDTO.getName())
                        .orElseThrow(() -> new RuntimeException("Tag not found: " + tagDTO.getName()));
                postTags.add(tag);
            }
            post.setTags(postTags);

            List<Author> postAuthors = new ArrayList<>();
            for (AuthorDTO authorDTO : postDTO.getAuthorsDetailed()) {
                Author author = authorRepository.findById(Long.parseLong(authorDTO.getId()))
                        .orElseThrow(() -> new RuntimeException("Author not found: " + authorDTO.getId()));
                postAuthors.add(author);
            }
            post.setAuthors(postAuthors);

            postToSave.add(post);
        }

        postRepository.saveAll(postToSave);

        log.info("Post created");

        return "Post created";
    }

    @Override
    public PostDTO copyPost(Long postId) {
        Post originalPost = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        Post copiedPost = new Post();
        copiedPost.setTitle(originalPost.getTitle() + " (Copy)");
        copiedPost.setSlug(generateUniqueSlug(originalPost.getSlug()));
        copiedPost.setStatus("draft");
        copiedPost.setLexical(originalPost.getLexical());
        copiedPost.setHtml(originalPost.getHtml());
        copiedPost.setTags(originalPost.getTags());
        copiedPost.setAuthors(originalPost.getAuthors());

        return convertToPostDTO(copiedPost);
    }

    private String generateUniqueSlug(String originalSlug) {
        String newSlug = originalSlug + "-copy";
        int count = 1;
        while (postRepository.existsBySlug(newSlug)) {
            newSlug = originalSlug + "-copy-" + count++;
        }
        return newSlug;
    }

    private PostDTO convertToPostDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setTitle(post.getTitle());
        dto.setSlug(post.getSlug());
        dto.setStatus(post.getStatus());
        dto.setLexical(post.getLexical());
        dto.setHtml(post.getHtml());

        List<String> authorIds = post.getAuthors().stream()
                .map(a -> String.valueOf(a.getId()))
                .collect(Collectors.toList());
        dto.setAuthors(authorIds);

        List<AuthorDTO> detailedAuthors = post.getAuthors().stream()
                .map(a -> {
                    AuthorDTO adto = new AuthorDTO();
                    adto.setId(String.valueOf(a.getId()));
                    return adto;
                })
                .collect(Collectors.toList());
        dto.setAuthorsDetailed(detailedAuthors);

        List<TagDTO> tagDTOs = post.getTags().stream()
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
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    log.error("Post not found with ID: {}", postId);
                    return new AppBadException("Post not found with ID: " + postId);
                });
        post.setTitle(newTitle);
        Post savedPost = postRepository.save(post);
        log.info("Post Title successfuly Updated with title : {}", savedPost.getTitle());
        return "Post Title updated";
    }

    @Override
    public String updatePostStatusPublished(Long postId, String status) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    log.error("Post not found with ID: {}", postId);
                    return new AppBadException("Post not found with ID: " + postId);
                });
        post.setStatus(status);
        Post savedPost = postRepository.save(post);
        log.info("Post Status successfuly Updated with status : {}", savedPost.getStatus());
        return "Post Status updated with status : " + savedPost.getStatus();
    }

    @Override
    public String updatePostStatusScheduled(Long postId, String status, String publishedAt) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    log.error("Post not found with ID: {}", postId);
                    return new AppBadException("Post not found with ID: " + postId);
                });
        post.setStatus(status);
        LocalDate localDate = LocalDate.parse(publishedAt);
        post.setPublishedAt(localDate.atStartOfDay());
        Post savedPost = postRepository.save(post);
        log.info("Post Status Scheduled Successfully Updated with status : {}", savedPost.getStatus());
        return "Post Status Updated with status : " + savedPost.getStatus() + " and publishedAt : " + publishedAt;
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
        Page<Post> postsPage = postRepository.findAll(pageable);

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

    private ActivePostsDTO toFullInfo(Post post) {
        List<TagDTO> tagDTOs = post.getTags().stream()
                .map(tag -> {
                    TagDTO dto = new TagDTO();
                    dto.setName(tag.getName());
                    dto.setDescription(tag.getDescription());
                    return dto;
                }).toList();

        List<AuthorDTO> authorDTOs = post.getAuthors().stream()
                .map(author -> {
                    AuthorDTO dto = new AuthorDTO();
                    dto.setId(String.valueOf(author.getId()));
                    return dto;
                }).toList();

        return new ActivePostsDTO(
                post.getId(),
                post.getTitle(),
                post.getLexical(),
                post.getHtml(),
                post.getStatus(),
                post.getSlug(),
                post.getPublishedAt(),
                tagDTOs,
                authorDTOs
        );
    }

}
