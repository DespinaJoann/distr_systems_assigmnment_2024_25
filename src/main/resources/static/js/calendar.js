const calendar = document.querySelector(".calendar");
const daysContainer = document.querySelector(".days");
const monthDisplay = document.querySelector(".display");
const leftButton = document.querySelector(".left");
const rightButton = document.querySelector(".right");

let today = new Date();
let month = today.getMonth();
let year = today.getFullYear();

/**
 * Δημιουργεί το ημερολόγιο για τον μήνα και τη χρονιά.
 */
function displayCalendar() {
    daysContainer.innerHTML = ""; // Καθαρισμός των προηγούμενων ημερών

    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0);
    const firstDayIndex = firstDay.getDay();
    const daysInMonth = lastDay.getDate();

    // Προσθήκη κενών πριν από την 1η ημέρα του μήνα
    for (let i = 0; i < firstDayIndex; i++) {
        daysContainer.innerHTML += "<div></div>";
    }

    // Προσθήκη όλων των ημερών του μήνα
    for (let day = 1; day <= daysInMonth; day++) {
        daysContainer.innerHTML += `<div>${day}</div>`;
    }

    monthDisplay.textContent = `${firstDay.toLocaleString("default", {
        month: "long"
    })} ${year}`;
}

/**
 * Προσθήκη events στο ημερολόγιο.
 */
function populateCalendar(events) {
    document.querySelectorAll(".days div").forEach((dayDiv) => {
        dayDiv.classList.remove("has-event");
        dayDiv.innerHTML = dayDiv.innerHTML.replace("🔔", "");
    });

    events.forEach(event => {
        const eventDate = new Date(event.date); // Μετατροπή της ημερομηνίας

        if (eventDate.getMonth() === month && eventDate.getFullYear() === year) {
            const dayDiv = document.querySelector(
                `.days div:nth-child(${eventDate.getDate() + firstDayIndex})`
            );

            if (dayDiv) {
                dayDiv.classList.add("has-event");
                dayDiv.innerHTML += " 🔔";
            }
        }
    });
}

// Αλλαγή μήνα
leftButton.addEventListener("click", () => {
    month--;
    if (month < 0) {
        month = 11;
        year--;
    }
    displayCalendar();
    populateCalendar(events);
});

rightButton.addEventListener("click", () => {
    month++;
    if (month > 11) {
        month = 0;
        year++;
    }
    displayCalendar();
    populateCalendar(events);
});

// Αρχική προβολή
displayCalendar();
populateCalendar(events);
