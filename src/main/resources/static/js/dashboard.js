
// A file including Dashboard Operations
function toggleUserInfo() {
    document.querySelector('.user-info').classList.toggle('open');
}

function toggleTheme() {
    const body = document.body;
    body.classList.toggle('dark-theme');
    document.getElementById('theme-toggle').textContent = body.classList.contains('dark-theme') ? '🌑' : '🌏';
}

function openModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) modal.style.display = "block";
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) modal.style.display = "none";
}

function toggleDetails(id) {
    const details = document.getElementById(id);
    if (details) details.style.display = details.style.display === "none" ? "block" : "none";
}