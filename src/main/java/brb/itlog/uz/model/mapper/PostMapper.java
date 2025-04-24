package brb.itlog.uz.model.mapper;

import brb.itlog.uz.model.dto.post.request.AuthorDTO;
import brb.itlog.uz.model.dto.post.request.CreatePostRequestDTO;
import brb.itlog.uz.model.dto.post.request.PostDTO;
import brb.itlog.uz.model.dto.post.request.TagDTO;
import brb.itlog.uz.model.entity.post.Authors;
import brb.itlog.uz.model.entity.post.Posts;
import brb.itlog.uz.model.entity.post.Tags;
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
    Posts toEntity(PostDTO postDTO);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Tags toEntity(TagDTO tagDTO);

    @Mapping(source = "id", target = "id")
    Authors toEntity(AuthorDTO authorDTO);

    List<Posts> toEntityList(List<PostDTO> postDTOs);

    default List<Posts> toPostList(CreatePostRequestDTO createPostRequestDTO) {
        return toEntityList(createPostRequestDTO.getPosts());
    }

    List<Tags> toTagList(List<TagDTO> tagDTOs);

    List<Authors> toAuthorList(List<AuthorDTO> authorDTOs);
}
