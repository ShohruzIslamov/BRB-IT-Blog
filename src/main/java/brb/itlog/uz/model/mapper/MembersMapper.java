package brb.itlog.uz.model.mapper;

import brb.itlog.uz.model.dto.members.request.MembersRequestDTO;
import brb.itlog.uz.model.dto.members.response.CreateMembersResponseDTO;
import brb.itlog.uz.model.dto.members.response.MembersResponseDTO;
import brb.itlog.uz.model.entity.member.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MembersMapper {

    Member toEntity(MembersRequestDTO membersRequestDTO);

    List<Member> toEntity(List<MembersRequestDTO> membersRequestDTOList);

    List<MembersResponseDTO> toDto(List<Member> memberList);

    default CreateMembersResponseDTO toCreateResponseDto(List<Member> memberList) {
        CreateMembersResponseDTO response = new CreateMembersResponseDTO();
        response.setMembers(toDto(memberList));
        return response;
    }


}
