package brb.itlog.uz.model.mapper;

import brb.itlog.uz.model.dto.newsletter.CreateNewsLetterDTO;
import brb.itlog.uz.model.dto.newsletter.UpdateNewsLetterDTO;
import brb.itlog.uz.model.entity.newsletter.Newsletters;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsLetterMapper {

    Newsletters toEntity(CreateNewsLetterDTO dto);

    CreateNewsLetterDTO toDto(Newsletters entity);

    List<Newsletters> toEntityCreate(List<CreateNewsLetterDTO> dtoList);

    List<Newsletters> toEntityUpdate(List<UpdateNewsLetterDTO> dtoList);

    List<CreateNewsLetterDTO> toDtoList(List<Newsletters> entityList);
}
