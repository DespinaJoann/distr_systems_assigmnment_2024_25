let currentRole = "";  // Μεταβλητή για την αποθήκευση του επιλεγμένου role

// Function that is triggered when a user selects a role
function signUpAs(role) {
    // Hide the original sign-up form options (volunteer, organization, admin buttons)
    document.querySelector('.login-form form').style.display = 'none';

    // Clear any previously generated form elements
    const formContainer = document.getElementById('dynamic-form');
    formContainer.innerHTML = '';

    // Define fields for each role
    const roleFields = {
        volunteer: [
            {label: 'Username', type: 'text', name: 'username', required: true},
            {label: 'Password', type: 'password', name: 'password', required: true},
            {label: 'Email', type: 'email', name: 'email', required: true},
            {label: 'First Name', type: 'text', name: 'firstName', required: true},
            {label: 'Last Name', type: 'text', name: 'lastName', required: true},
            {label: 'Phone Number', type: 'tel', name: 'phoneNumber', required: true},
            {label: 'Date of Birth', type: 'date', name: 'dateOfBirth'},
            {label: 'Profile Description', type: 'textarea', name: 'profileDescription'}
        ],
        organization: [
            {label: 'Username', type: 'text', name: 'username', required: true},
            {label: 'Password', type: 'password', name: 'password', required: true},
            {label: 'Email', type: 'email', name: 'email', required: true},
            {label: 'Organization Name', type: 'text', name: 'organizationName', required: true},
            {label: 'Phone Number', type: 'tel', name: 'phoneNumber', required: true},
            {label: 'Address', type: 'text', name: 'address'},
            {label: 'Location', type: 'text', name: 'location'},
            {label: 'Profile Description', type: 'textarea', name: 'profileDescription'},
            {label: 'Organization Type', type: 'text', name: 'organizationType'}
        ],
        admin: [
            {label: 'Username', type: 'text', name: 'username', required: true},
            {label: 'Password', type: 'password', name: 'password', required: true},
            {label: 'Email', type: 'email', name: 'email', required: true},
            {label: 'First Name', type: 'text', name: 'firstName', required: true},
            {label: 'Last Name', type: 'text', name: 'lastName', required: true},
            {label: 'Special Admin Key', type: 'text', name: 'specialAdminKey', required: true},
        ]
    };

    // Save the role for later use
    currentRole = role;

    // Generate form dynamically based on role
    createForm(roleFields[role], role);
}

// Function to create forms dynamically
function createForm(fields, role) {
    const formContainer = document.getElementById('dynamic-form');
    const form = document.createElement('form');
    form.setAttribute('id', `${role}-form`);

    // Create fields dynamically
    fields.forEach(field => {
        const fieldDiv = document.createElement('div');
        const label = document.createElement('label');
        label.setAttribute('for', field.name);
        label.innerText = field.label;

        const input = document.createElement(field.type === 'textarea' ? 'textarea' : 'input');
        input.setAttribute('type', field.type);
        input.setAttribute('name', field.name);
        input.setAttribute('id', field.name);
        input.setAttribute('placeholder', field.label);

        if (field.required) {
            input.setAttribute('required', true);
        }

        fieldDiv.appendChild(label);
        fieldDiv.appendChild(input);
        form.appendChild(fieldDiv);
    });

    // Add Role Field (hidden, predefined as role)
    const roleField = document.createElement('input');
    roleField.setAttribute('type', 'hidden');
    roleField.setAttribute('name', 'role');
    roleField.setAttribute('value', role);
    form.appendChild(roleField);

    // Submit Button for the form
    const submitButton = document.createElement('button');
    submitButton.setAttribute('type', 'button'); // Make it type="button" to prevent form submission
    submitButton.innerText = `Sign Up as ${role.charAt(0).toLowerCase() + role.slice(1)}`;
    submitButton.onclick = submitForm; // Assign the submitForm function
    form.appendChild(submitButton);

    // Append form to container
    formContainer.appendChild(form);
}

// Function to handle form submission via AJAX
function submitForm() {
    const form = document.querySelector('#dynamic-form form');
    const formData = new FormData(form);
    const data = {};

    // Convert form data to a JSON object
    formData.forEach((value, key) => {
        data[key] = value;
    });

    // Add the role to the data object
    data["role"] = currentRole;

    // Send the form data to the server via fetch (AJAX)
    fetch('/auth/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',  // Specify that we're sending JSON data
        },
        body: JSON.stringify(data) // Send the form data as JSON
    })
        .then(response => {
            // Ensure that the response is OK
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the JSON
        })
        .then(data => {
            console.log('Success:', data);
            alert('Signup successful!');
            window.location.href = '/signup_success.html';
        })
        .catch(error => {
            console.error('Error:', error);
            alert('There was an error.');
            window.location.href = '/signup_error.html';
        });
}
