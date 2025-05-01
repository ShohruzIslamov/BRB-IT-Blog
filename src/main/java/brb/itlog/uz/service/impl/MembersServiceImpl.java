package brb.itlog.uz.service.impl;

import brb.itlog.uz.exception.AppBadException;
import brb.itlog.uz.model.dto.members.request.*;
import brb.itlog.uz.model.dto.members.response.CreateMembersResponseDTO;
import brb.itlog.uz.model.entity.label.Label;
import brb.itlog.uz.model.entity.member.Member;
import brb.itlog.uz.model.entity.newsletter.Newsletter;
import brb.itlog.uz.model.mapper.MembersMapper;
import brb.itlog.uz.repository.LabelsRepository;
import brb.itlog.uz.repository.MembersRepository;
import brb.itlog.uz.repository.NewsLetterRepository;
import brb.itlog.uz.service.MembersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MembersServiceImpl implements MembersService {

    private final MembersRepository membersRepository;
    private final MembersMapper membersMapper;
    private final LabelsRepository labelsRepository;
    private final NewsLetterRepository newsletterRepository;

    @Override
    public CreateMembersResponseDTO createMember(CreateMembersRequestDTO createMembersRequestDTO) {
        List<Member> memberList = membersMapper.toEntity(createMembersRequestDTO.getMembers());

        for (Member member : memberList) {
            List<Label> attachedLabels = new ArrayList<>();
            for (Label label : member.getLabels()) {
                Label attachedLabel = labelsRepository.findByName(label.getName())
                        .orElseThrow(() -> new RuntimeException("Label not found: " + label.getName()));
                attachedLabels.add(attachedLabel);
            }
            member.setLabels(attachedLabels);

        }

        for (Member member : memberList) {
            membersRepository.save(member);
        }

        return membersMapper.toCreateResponseDto(memberList);
    }

    @Override
    public String updateMember(Long memberId, UpdateMembersRequestDTO updateMembersRequestDTO) {
        Member member = membersRepository.findById(memberId)
                .orElseThrow(() -> {
                    log.error("Member not found: " + memberId);
                    return new AppBadException("Member not found");
                });

        MembersRequestDTO updateMemberDTO = updateMembersRequestDTO.getMembers().get(0);
        member.setEmail(updateMemberDTO.getEmail());
        member.setName(updateMemberDTO.getName());
        member.setNote(updateMemberDTO.getNote());

        if (updateMemberDTO.getLabels() != null && !updateMemberDTO.getLabels().isEmpty()) {
            List<Label> attachedLabels = new ArrayList<>();
            for (LabelRequestDTO labelRequestDTO : updateMemberDTO.getLabels()) {
                Label label = labelsRepository.findByName(labelRequestDTO.getName())
                        .orElseThrow(() -> new RuntimeException("Label not found: " + labelRequestDTO.getName()));
                attachedLabels.add(label);
            }
            member.setLabels(attachedLabels);
        }

        if (updateMemberDTO.getNewsletters() != null && !updateMemberDTO.getNewsletters().isEmpty()) {
            List<Newsletter> attachedNewsletters = new ArrayList<>();
            for (NewsletterRequestDTO newsletterRequestDTO : updateMemberDTO.getNewsletters()) {
                Newsletter newsletter = newsletterRepository.findById(newsletterRequestDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Newsletter not found: " + newsletterRequestDTO.getId()));
                attachedNewsletters.add(newsletter);
            }
            member.setNewsletters(attachedNewsletters);
        }

        membersRepository.save(member);

        return "Member successfully updated";
    }
}
