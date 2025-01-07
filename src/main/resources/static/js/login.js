// Variable for saving the chosen role
let currentRole = "";

// Function that is triggered when a user selects a role
function loginAs (role) {

    // Hide the original log-in form options (volunteer, organization, admin -> buttons)
    document.querySelector('.login-form form').style.display = 'none';

    // Clear any previously generated form elements
    const formContainer = document.getElementById('dynamic-form');
    formContainer.innerHTML = '';

    // Define fields for each role
    const roleFields = {
        volunteer: [
            {label: 'Username', type: 'text', name: 'username', required: true},
            {label: 'Password', type: 'password', name: 'password', required: true},
        ],
        organization: [
            {label: 'Username', type: 'text', name: 'username', required: true},
            {label: 'Password', type: 'password', name: 'password', required: true},
        ],
        admin: [
            {label: 'Username', type: 'text', name: 'username', required: true},
            {label: 'Password', type: 'password', name: 'password', required: true},
            {label: 'Special Admin Key', type: 'text', name: 'specialAdminKey', required: true}
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
    submitButton.innerText = `Login as ${role.charAt(0).toUpperCase() + role.slice(1)}`;
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

    // Μετατροπή FormData σε JSON αντικείμενο
    formData.forEach((value, key) => {
        data[key] = value;
    });

    // Αντιστοιχίστε τον ρόλο (ROLE_VOLUNTEER, ROLE_ORGANIZATION, ROLE_ADMIN) με βάση την επιλογή του χρήστη
    const roleMapping = {
        volunteer: "ROLE_VOLUNTEER",
        organization: "ROLE_ORGANIZATION",
        admin: "ROLE_ADMIN"
    };

    // Προσθέστε τον ρόλο με βάση την επιλογή του χρήστη
    data["role"] = roleMapping[currentRole];  // Εδώ ο ρόλος πρέπει να αποστέλλεται στο backend για login

    // Ενημερωμένο AJAX αίτημα για login στο /auth/login
    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)  // Στείλτε τα δεδομένα ως JSON
    })
        .then(response => response.json())
        .then(data => {
            if (data.redirect) {
                window.location.href = data.redirect; // Ανακατεύθυνση
            } else {
                alert('Login succeeded, but no redirect URL provided.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('There was an error during login.');
        });
}
