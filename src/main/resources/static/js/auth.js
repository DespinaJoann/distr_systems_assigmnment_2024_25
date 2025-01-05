// Get the query parameters from the URL
const params = new URLSearchParams(window.location.search);
// Extract the 'type' parameter to determine whether it's a login or signup action
const type = params.get("type");

// Find the container element where the form will be displayed
const authContainer = document.getElementById("authContainer");

// Dynamically generate the form based on the 'type' parameter
if (type === "login") {
    // If the type is 'login', create the login form
    authContainer.innerHTML = `
        <h2>Login</h2>
        <form id="loginForm">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <label for="signAs">Sign in as:</label>
            <select id="signAs" name="signAs" required>
                <option value="admin">Admin</option>
                <option value="volunteer">Volunteer</option>
                <option value="organization">Organization</option>
            </select>
            <button type="submit">Login</button>
        </form>
    `;
    // Attach the event listener to handle form submission for login
    handleLoginForm();
} else if (type === "signup") {
    // If the type is 'signup', create the signup form
    authContainer.innerHTML = `
        <h2>Sign Up</h2>
        <form id="signUpForm">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <label for="role">Role:</label>
            <select id="role" name="role" required>
                <option value="admin">Admin</option>
                <option value="volunteer">Volunteer</option>
                <option value="organization">Organization</option>
            </select>
            <button type="submit">Sign Up</button>
        </form>
    `;
    // Attach the event listener to handle form submission for signup
    handleSignUpForm();
} else {
    // If the 'type' parameter is invalid, show an error message
    authContainer.innerHTML = `<p>Invalid type specified. Please go back.</p>`;
}

// Function to handle login form submission
function handleLoginForm() {
    const loginForm = document.getElementById("loginForm");
    loginForm.addEventListener("submit", async function (event) {
        event.preventDefault(); // Prevent the default form submission behavior

        // Get the input values from the login form
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;
        const signAs = document.getElementById("signAs").value;

        try {
            // Make a POST request to the server to handle login
            const response = await fetch(`/auth/login`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, password, signAs }), // Send the login data as JSON
            });

            // Check if the response is successful
            if (response.ok) {
                const data = await response.json();
                alert("Login successful! Welcome, " + data.username); // Display a success message

                // Redirect to the dashboard after successful login
                window.location.href = "/dashboard.html";
            } else {
                // If the response is not OK, display the error message
                const error = await response.text();
                alert("Login failed: " + error);
            }
        } catch (err) {
            // Handle any unexpected errors
            alert("An error occurred: " + err.message);
        }
    });
}

// Function to handle signup form submission
function handleSignUpForm() {
    const signUpForm = document.getElementById("signUpForm");
    signUpForm.addEventListener("submit", async function (event) {
        event.preventDefault(); // Prevent the default form submission behavior

        // Get the input values from the signup form
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;
        const role = document.getElementById("role").value;

        try {
            // Make a POST request to the server to handle signup
            const response = await fetch(`/auth/signup`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, password, role }), // Send the signup data as JSON
            });

            // Check if the response is successful
            if (response.ok) {
                const data = await response.json();
                alert("Sign Up successful! Welcome, " + data.username); // Display a success message

                // Redirect to the login page after successful signup
                window.location.href = "auth.html?type=login";
            } else {
                // If the response is not OK, display the error message
                const error = await response.text();
                alert("Sign Up failed: " + error);
            }
        } catch (err) {
            // Handle any unexpected errors
            alert("An error occurred: " + err.message);
        }
    });
}
