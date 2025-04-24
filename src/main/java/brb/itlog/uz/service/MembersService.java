package brb.itlog.uz.service;

import brb.itlog.uz.model.dto.members.request.CreateMembersRequestDTO;
import brb.itlog.uz.model.dto.members.request.UpdateMembersRequestDTO;
import brb.itlog.uz.model.dto.members.response.CreateMembersResponseDTO;

public interface MembersService {

    CreateMembersResponseDTO createMember(CreateMembersRequestDTO createMembersRequestDTO);

    String updateMember(Long memberId, UpdateMembersRequestDTO updateMembersRequestDTO);

}
