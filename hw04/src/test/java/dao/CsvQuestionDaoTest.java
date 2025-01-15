package dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.exceptions.QuestionReadException;
import static org.assertj.core.api.Assertions.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = CsvQuestionDao.class)
@DisplayName("Метод findAll() должен ")
public class CsvQuestionDaoTest {

    private QuestionDao questionDao;

    @MockitoBean
    private AppProperties appProperties;

    @BeforeEach
    void setUp() {
        questionDao = new CsvQuestionDao(appProperties);
    }

    @Test
    @DisplayName(" не бросать исключение если файл найден")
    public void shouldNotThrowQuestionReadExceptionIfQuestionsFileExists() {
        given(appProperties.getTestFileName()).willReturn("test_questions.csv");
        assertThatCode(() -> questionDao.findAll()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName(" бросать исключение если файл не найден")
    public void shouldThrowQuestionReadExceptionIfQuestionsFileNotExists() {
        given(appProperties.getTestFileName()).willReturn("questions_not_exist.csv");
        assertThatThrownBy(() -> questionDao.findAll()).isInstanceOf(QuestionReadException.class);
    }

    @Test
    @DisplayName(" вернуть список вопросов не пустой")
    public void shouldReturnQuestionsListNotNull() {
        given(appProperties.getTestFileName()).willReturn("test_questions.csv");
        var list = questionDao.findAll();
        assertNotNull(list);
    }
}


