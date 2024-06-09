document.addEventListener('DOMContentLoaded', function () {
    console.log('Documento cargado');

    const especialidadForm = document.getElementById('especialidadForm');
    const especialidadNombre = document.getElementById('especialidadNombre');
    const especialidadesList = document.getElementById('especialidadesList');

    const medicoForm = document.getElementById('medicoForm');
    const medicoNombre = document.getElementById('medicoNombre');
    const medicoEspecialidad = document.getElementById('medicoEspecialidad');
    const medicosList = document.getElementById('medicosList');

    const pacienteForm = document.getElementById('pacienteForm');
    const pacienteNombre = document.getElementById('pacienteNombre');
    const pacienteFechaNacimiento = document.getElementById('pacienteFechaNacimiento');
    const pacientesList = document.getElementById('pacientesList');

    const diagnosticoForm = document.getElementById('diagnosticoForm');
    const diagnosticoNombre = document.getElementById('diagnosticoNombre');
    const diagnosticosList = document.getElementById('diagnosticosList');

    const ingresoForm = document.getElementById('ingresoForm');
    const ingresoPaciente = document.getElementById('ingresoPaciente');
    const ingresoMedico = document.getElementById('ingresoMedico');
    const ingresoEspecialidad = document.getElementById('ingresoEspecialidad');
    const ingresoDiagnostico = document.getElementById('ingresoDiagnostico');
    const ingresoFechaIngreso = document.getElementById('ingresoFechaIngreso');
    const ingresoFechaAlta = document.getElementById('ingresoFechaAlta');
    const ingresosList = document.getElementById('ingresosList');

    // Funciones para obtener datos y llenar listas
    function fetchEspecialidades() {
        console.log('Fetching especialidades');
        fetch('/api/especialidades')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al obtener especialidades');
                }
                return response.json();
            })
            .then(data => {
                especialidadesList.innerHTML = '';
                medicoEspecialidad.innerHTML = '<option value="" disabled selected>Seleccione Especialidad</option>';
                ingresoEspecialidad.innerHTML = '<option value="" disabled selected>Seleccione Especialidad</option>';
                data.forEach(especialidad => {
                    const li = document.createElement('li');
                    li.textContent = especialidad.nombre;
                    especialidadesList.appendChild(li);

                    const option = document.createElement('option');
                    option.value = especialidad.id;
                    option.textContent = especialidad.nombre;
                    medicoEspecialidad.appendChild(option);
                    ingresoEspecialidad.appendChild(option);
                });
            })
            .catch(error => console.error('Error:', error));
    }

    function fetchMedicos() {
        console.log('Fetching medicos');
        fetch('/api/medicos')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al obtener medicos');
                }
                return response.json();
            })
            .then(data => {
                medicosList.innerHTML = '';
                ingresoMedico.innerHTML = '<option value="" disabled selected>Seleccione Médico</option>';
                data.forEach(medico => {
                    const li = document.createElement('li');
                    li.textContent = medico.nombre;
                    medicosList.appendChild(li);

                    const option = document.createElement('option');
                    option.value = medico.id;
                    option.textContent = medico.nombre;
                    ingresoMedico.appendChild(option);
                });
            })
            .catch(error => console.error('Error:', error));
    }

    function fetchPacientes() {
        console.log('Fetching pacientes');
        fetch('/api/pacientes')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al obtener pacientes');
                }
                return response.json();
            })
            .then(data => {
                pacientesList.innerHTML = '';
                ingresoPaciente.innerHTML = '<option value="" disabled selected>Seleccione Paciente</option>';
                data.forEach(paciente => {
                    const li = document.createElement('li');
                    li.textContent = paciente.nombre;
                    pacientesList.appendChild(li);

                    const option = document.createElement('option');
                    option.value = paciente.id;
                    option.textContent = paciente.nombre;
                    ingresoPaciente.appendChild(option);
                });
            })
            .catch(error => console.error('Error:', error));
    }

    function fetchDiagnosticos() {
        console.log('Fetching diagnosticos');
        fetch('/api/diagnosticos')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al obtener diagnosticos');
                }
                return response.json();
            })
            .then(data => {
                diagnosticosList.innerHTML = '';
                ingresoDiagnostico.innerHTML = '<option value="" disabled selected>Seleccione Diagnóstico</option>';
                data.forEach(diagnostico => {
                    const li = document.createElement('li');
                    li.textContent = diagnostico.nombre;
                    diagnosticosList.appendChild(li);

                    const option = document.createElement('option');
                    option.value = diagnostico.id;
                    option.textContent = diagnostico.nombre;
                    ingresoDiagnostico.appendChild(option);
                });
            })
            .catch(error => console.error('Error:', error));
    }

    function fetchIngresos() {
        console.log('Fetching ingresos');
        fetch('/api/ingresos')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al obtener ingresos');
                }
                return response.json();
            })
            .then(data => {
                ingresosList.innerHTML = '';
                data.forEach(ingreso => {
                    const li = document.createElement('li');
                    li.textContent = `Paciente: ${ingreso.paciente.nombre}, Médico: ${ingreso.medico.nombre}, Especialidad: ${ingreso.especialidad.nombre}, Diagnóstico: ${ingreso.diagnostico.nombre}, Ingreso: ${ingreso.fechaIngreso}, Alta: ${ingreso.fechaAlta || 'N/A'}`;
                    ingresosList.appendChild(li);
                });
            })
            .catch(error => console.error('Error:', error));
    }

    // Funciones para agregar nuevos datos
    especialidadForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const nuevaEspecialidad = { nombre: especialidadNombre.value };

        fetch('/api/especialidades', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevaEspecialidad)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al agregar especialidad');
                }
                return response.json();
            })
            .then(data => {
                especialidadNombre.value = '';
                fetchEspecialidades();
            })
            .catch(error => console.error('Error:', error));
    });

    medicoForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const nuevoMedico = { nombre: medicoNombre.value, especialidad_id: medicoEspecialidad.value };

        fetch('/api/medicos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoMedico)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al agregar medico');
                }
                return response.json();
            })
            .then(data => {
                medicoNombre.value = '';
                medicoEspecialidad.selectedIndex = 0;
                fetchMedicos();
            })
            .catch(error => console.error('Error:', error));
    });

    pacienteForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const nuevoPaciente = { nombre: pacienteNombre.value, fecha_nacimiento: pacienteFechaNacimiento.value };

        fetch('/api/pacientes', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoPaciente)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al agregar paciente');
                }
                return response.json();
            })
            .then(data => {
                pacienteNombre.value = '';
                pacienteFechaNacimiento.value = '';
                fetchPacientes();
            })
            .catch(error => console.error('Error:', error));
    });

    diagnosticoForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const nuevoDiagnostico = { nombre: diagnosticoNombre.value };

        fetch('/api/diagnosticos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoDiagnostico)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al agregar diagnostico');
                }
                return response.json();
            })
            .then(data => {
                diagnosticoNombre.value = '';
                fetchDiagnosticos();
            })
            .catch(error => console.error('Error:', error));
    });

    ingresoForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const nuevoIngreso = {
            paciente_id: ingresoPaciente.value,
            medico_id: ingresoMedico.value,
            especialidad_id: ingresoEspecialidad.value,
            diagnostico_id: ingresoDiagnostico.value,
            fecha_ingreso: ingresoFechaIngreso.value,
            fecha_alta: ingresoFechaAlta.value
        };

        fetch('/api/ingresos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoIngreso)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al agregar ingreso');
                }
                return response.json();
            })
            .then(data => {
                ingresoPaciente.selectedIndex = 0;
                ingresoMedico.selectedIndex = 0;
                ingresoEspecialidad.selectedIndex = 0;
                ingresoDiagnostico.selectedIndex = 0;
                ingresoFechaIngreso.value = '';
                ingresoFechaAlta.value = '';
                fetchIngresos();
            })
            .catch(error => console.error('Error:', error));
    });

    // Cargar todos los datos al cargar la página
    fetchEspecialidades();
    fetchMedicos();
    fetchPacientes();
    fetchDiagnosticos();
    fetchIngresos();
});
