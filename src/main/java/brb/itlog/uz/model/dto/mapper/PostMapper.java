package brb.itlog.uz.model.dto.mapper;

import brb.itlog.uz.model.dto.post.request.AuthorDTO;
import brb.itlog.uz.model.dto.post.request.CreatePostRequestDTO;
import brb.itlog.uz.model.dto.post.request.PostDTO;
import brb.itlog.uz.model.dto.post.request.TagDTO;
import brb.itlog.uz.model.entity.Author;
import brb.itlog.uz.model.entity.Post;
import brb.itlog.uz.model.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    // PostDTO -> Post
    @Mapping(source = "title", target = "title")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "lexical", target = "lexical")
    @Mapping(source = "html", target = "html")
    @Mapping(source = "tagsDetailed", target = "tags")
    @Mapping(source = "authorsDetailed", target = "authors")
    Post toEntity(PostDTO postDTO);

    // TagDTO -> Tag
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Tag toEntity(TagDTO tagDTO);

    // AuthorDTO -> Author
    @Mapping(source = "id", target = "id")
    Author toEntity(AuthorDTO authorDTO);

    // Convert List<PostDTO> to List<Post>
    List<Post> toEntityList(List<PostDTO> postDTOs);

    // Convert CreatePostRequestDTO to List<Post>
    default List<Post> toPostList(CreatePostRequestDTO createPostRequestDTO) {
        return toEntityList(createPostRequestDTO.getPosts());
    }

    // Convert List<TagDTO> to List<Tag>
    List<Tag> toTagList(List<TagDTO> tagDTOs);

    // Convert List<AuthorDTO> to List<Author>
    List<Author> toAuthorList(List<AuthorDTO> authorDTOs);
}
