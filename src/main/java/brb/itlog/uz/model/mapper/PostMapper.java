package brb.itlog.uz.model.mapper;

import brb.itlog.uz.model.dto.post.request.AuthorDTO;
import brb.itlog.uz.model.dto.post.request.CreatePostRequestDTO;
import brb.itlog.uz.model.dto.post.request.PostDTO;
import brb.itlog.uz.model.dto.post.request.TagDTO;
import brb.itlog.uz.model.entity.post.Author;
import brb.itlog.uz.model.entity.post.Post;
import brb.itlog.uz.model.entity.post.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "title", target = "title")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "lexical", target = "lexical")
    @Mapping(source = "html", target = "html")
    @Mapping(source = "tagsDetailed", target = "tags")
    @Mapping(source = "authorsDetailed", target = "authors")
    Post toEntity(PostDTO postDTO);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Tag toEntity(TagDTO tagDTO);

    @Mapping(source = "id", target = "id")
    Author toEntity(AuthorDTO authorDTO);

    List<Post> toEntityList(List<PostDTO> postDTOs);

    default List<Post> toPostList(CreatePostRequestDTO createPostRequestDTO) {
        return toEntityList(createPostRequestDTO.getPosts());
    }

    List<Tag> toTagList(List<TagDTO> tagDTOs);

    List<Author> toAuthorList(List<AuthorDTO> authorDTOs);
}
