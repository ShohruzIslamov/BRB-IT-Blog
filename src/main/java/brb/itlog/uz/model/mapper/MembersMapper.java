package brb.itlog.uz.model.mapper;

import brb.itlog.uz.model.dto.members.request.MembersRequestDTO;
import brb.itlog.uz.model.dto.members.response.CreateMembersResponseDTO;
import brb.itlog.uz.model.dto.members.response.MembersResponseDTO;
import brb.itlog.uz.model.entity.member.Members;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MembersMapper {

    Members toEntity(MembersRequestDTO membersRequestDTO);

    List<Members> toEntity(List<MembersRequestDTO> membersRequestDTOList);

    List<MembersResponseDTO> toDto(List<Members> membersList);

    default CreateMembersResponseDTO toCreateResponseDto(List<Members> membersList) {
        CreateMembersResponseDTO response = new CreateMembersResponseDTO();
        response.setMembers(toDto(membersList));
        return response;
    }


}
