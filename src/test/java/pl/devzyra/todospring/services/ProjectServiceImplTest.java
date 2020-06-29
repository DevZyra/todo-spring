package pl.devzyra.todospring.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.devzyra.todospring.config.TaskConfigProperties;
import pl.devzyra.todospring.repositories.ProjectRepository;
import pl.devzyra.todospring.repositories.TaskGroupRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {





    @Test
    @DisplayName("Group creation test - IllegalStateException")
    void createGroup_noMultipleAllowed_And_OpenGroup_throwsIllegalStateException() {

        // given
        var mockTaskGroup = mock(TaskGroupRepository.class);
        when(mockTaskGroup.existsByDoneIsFalseAndProject_Id(anyLong())).thenReturn(true);
        // and
        TaskConfigProperties mockConfig = configurationReturning(false);
        // under test
        var toTest = new ProjectServiceImpl(null,mockTaskGroup,mockConfig);

        // when + then

  /*    assertThatThrownBy(()->{
            toTest.createGroup(LocalDateTime.now(),0L);
        }).isInstanceOf(IllegalStateException.class);       */

  /*    assertThatExceptionOfType(IllegalStateException.class).isThrownBy(()->{
            toTest.createGroup(LocalDateTime.now(),0L);
        });                                                 */

  /*    assertThatIllegalStateException().isThrownBy(()->{
            toTest.createGroup(LocalDateTime.now(),0L);
        });                                                 */

        var toCatch = catchThrowable(()->toTest.createGroup(LocalDateTime.now(),0L));
        assertThat(toCatch).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("undone group");
    }

    @Test
    @DisplayName("IllegalArgumentException thrown by no project with that id")
    void createGroup_AllowMultipleTasks_but_noExistingId(){
        //given
        var mockProjectRepository = mock(ProjectRepository.class);
        when(mockProjectRepository.findById(anyLong())).thenReturn(Optional.empty());
        // and
        TaskConfigProperties mockConfig = configurationReturning(true);
        // under test
        var toTest = new ProjectServiceImpl(mockProjectRepository,null,mockConfig);

        // when
        var exception = catchThrowable(()-> toTest.createGroup(LocalDateTime.now(),1L));
        // then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("given id not found");
    }

  


    private TaskConfigProperties configurationReturning(boolean b) {
        var mockTemplate = mock(TaskConfigProperties.Template.class);
        when(mockTemplate.getAllowMultipleTasks()).thenReturn(b);
        // and
        var mockConfig = mock(TaskConfigProperties.class);
        when(mockConfig.getTemplate()).thenReturn(mockTemplate);
        return mockConfig;
    }
}