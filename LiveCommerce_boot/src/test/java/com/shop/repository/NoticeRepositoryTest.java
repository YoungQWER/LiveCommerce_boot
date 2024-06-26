package com.shop.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.entity.Notice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class NoticeRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NoticeRepository noticeRepository;

    @PersistenceContext
    private EntityManager em;

    private Notice mockNotice;

    @BeforeEach
    public void setUp() {
        // 테스트용 Mock 데이터 설정
        mockNotice = new Notice();
        mockNotice.setTitle("2323");
        mockNotice.setContent("2323");
        mockNotice.setAuthor("qwer");

        // 공지사항을 데이터베이스에 저장
        noticeRepository.save(mockNotice);
    }

    @Test
    @DisplayName("공지사항 삭제 테스트")
    @WithMockUser(username = "qwer", roles = {"ADMIN"})
    public void deleteNoticeTest() throws Exception {
        // given
        Long noticeId = mockNotice.getId();

        // when
        ResultActions resultActions = mockMvc.perform(
                delete("/api/notices/{id}", noticeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk());

        // 삭제된 공지사항이 데이터베이스에 없는지 확인
        Optional<Notice> deletedNotice = noticeRepository.findById(noticeId);
        if (deletedNotice.isPresent()) {
            throw new AssertionError("Expected notice to be deleted");
        }
    }
}
