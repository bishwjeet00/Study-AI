package com.studyai.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studyai.domain.model.Student
import com.studyai.domain.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {

    private val _studentState = MutableStateFlow<StudentUiState>(StudentUiState.Idle)
    val studentState: StateFlow<StudentUiState> = _studentState

    private val _student = MutableStateFlow<Student?>(null)
    val student: StateFlow<Student?> = _student

    fun getStudent(studentId: String) {
        _studentState.value = StudentUiState.Loading
        viewModelScope.launch {
            studentRepository.getStudent(studentId).collect { student ->
                _student.value = student
                _studentState.value = if (student != null) {
                    StudentUiState.Success
                } else {
                    StudentUiState.Error("Student not found")
                }
            }
        }
    }

    fun createStudent(student: Student) {
        _studentState.value = StudentUiState.Loading
        viewModelScope.launch {
            try {
                val result = studentRepository.createStudent(student)
                result.onSuccess { createdStudent ->
                    _student.value = createdStudent
                    _studentState.value = StudentUiState.Success
                }.onFailure { exception ->
                    Timber.e(exception, "Error creating student")
                    _studentState.value = StudentUiState.Error(exception.message ?: "Unknown error")
                }
            } catch (e: Exception) {
                Timber.e(e, "Exception creating student")
                _studentState.value = StudentUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class StudentUiState {
    object Idle : StudentUiState()
    object Loading : StudentUiState()
    object Success : StudentUiState()
    data class Error(val message: String) : StudentUiState()
}
