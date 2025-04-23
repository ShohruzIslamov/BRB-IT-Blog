package brb.itlog.uz.model.pagination;

import brb.itlog.uz.model.dto.post.request.AuthorDTO;
import brb.itlog.uz.model.dto.post.request.TagDTO;
import brb.itlog.uz.model.entity.Author;
import brb.itlog.uz.model.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ActivePostsDTO {

    private Long id;
    private String title;
    private String lexical;
    private String html;
    private String status;
    private String slug;
    private LocalDateTime publishedAt;
    private List<TagDTO> tags;
    private List<AuthorDTO> authors;

    public ActivePostsDTO(Long id, String title, String lexical, String html, String status, String slug, LocalDateTime publishedAt, List<TagDTO> tags, List<AuthorDTO> authors) {
        this.id = id;
        this.title = title;
        this.lexical = lexical;
        this.html = html;
        this.status = status;
        this.slug = slug;
        this.publishedAt = publishedAt;
        this.tags = tags;
        this.authors = authors;
    }
}

