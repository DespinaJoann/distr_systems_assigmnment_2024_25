
// Handle the Login button click
document.getElementById("loginButton")
        .addEventListener("click", function() {
    window.location.href = "auth.html?type=login";  // Redirect to login page
});

// Handle the SignUp button click
document.getElementById("signUpButton")
        .addEventListener("click", function() {
    window.location.href = "auth.html?type=signup";  // Redirect to sign up page
});
