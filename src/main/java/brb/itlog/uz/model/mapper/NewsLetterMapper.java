package brb.itlog.uz.model.mapper;

import brb.itlog.uz.model.dto.newsletter.CreateNewsLetterDTO;
import brb.itlog.uz.model.dto.newsletter.UpdateNewsLetterDTO;
import brb.itlog.uz.model.entity.newsletter.Newsletter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsLetterMapper {

    Newsletter toEntity(CreateNewsLetterDTO dto);

    CreateNewsLetterDTO toDto(Newsletter entity);

    List<Newsletter> toEntityCreate(List<CreateNewsLetterDTO> dtoList);

    List<Newsletter> toEntityUpdate(List<UpdateNewsLetterDTO> dtoList);

    List<CreateNewsLetterDTO> toDtoList(List<Newsletter> entityList);
}
