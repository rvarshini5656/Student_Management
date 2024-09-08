document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('student-form');
    const studentIdInput = document.getElementById('student-id');
    const studentNameInput = document.getElementById('student-name');
    const studentGradesInput = document.getElementById('student-grades');
    const addBtn = document.getElementById('add-btn');
    const updateBtn = document.getElementById('update-btn');
    const deleteBtn = document.getElementById('delete-btn');
    const studentsList = document.getElementById('students');

    // Load existing students from localStorage
    loadStudents();

    form.addEventListener('submit', (event) => {
        event.preventDefault();
        addStudent(studentIdInput.value, studentNameInput.value, studentGradesInput.value);
    });

    updateBtn.addEventListener('click', () => {
        updateStudent(studentIdInput.value, studentNameInput.value, studentGradesInput.value);
    });

    deleteBtn.addEventListener('click', () => {
        deleteStudent(studentIdInput.value);
    });

    function addStudent(id, name, grades) {
        if (!id || !name || !grades) {
            alert('All fields are required.');
            return;
        }

        let students = getStudentsFromLocalStorage();
        if (students.some(student => student.id === id)) {
            alert('Student ID already exists.');
            return;
        }

        students.push({ id, name, grades });
        saveStudentsToLocalStorage(students);
        loadStudents();
        clearForm();
    }

    function updateStudent(id, name, grades) {
        if (!id || !name || !grades) {
            alert('All fields are required.');
            return;
        }

        let students = getStudentsFromLocalStorage();
        let student = students.find(student => student.id === id);
        if (!student) {
            alert('Student ID not found.');
            return;
        }

        student.name = name;
        student.grades = grades;
        saveStudentsToLocalStorage(students);
        loadStudents();
        clearForm();
    }

    function deleteStudent(id) {
        let students = getStudentsFromLocalStorage();
        let filteredStudents = students.filter(student => student.id !== id);
        if (students.length === filteredStudents.length) {
            alert('Student ID not found.');
            return;
        }

        saveStudentsToLocalStorage(filteredStudents);
        loadStudents();
        clearForm();
    }

    function loadStudents() {
        let students = getStudentsFromLocalStorage();
        studentsList.innerHTML = students.map(student => `
            <li>${student.name} (ID: ${student.id}, Grades: ${student.grades})</li>
        `).join('');
    }

    function getStudentsFromLocalStorage() {
        let students = localStorage.getItem('students');
        return students ? JSON.parse(students) : [];
    }

    function saveStudentsToLocalStorage(students) {
        localStorage.setItem('students', JSON.stringify(students));
    }

    function clearForm() {
        studentIdInput.value = '';
        studentNameInput.value = '';
        studentGradesInput.value = '';
    }
});
