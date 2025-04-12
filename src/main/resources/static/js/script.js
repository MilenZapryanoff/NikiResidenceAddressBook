document.addEventListener("DOMContentLoaded", function () {
    const residentsContainer = document.getElementById("residents-container");
    const residents = residentsContainer.querySelectorAll(".resident");
    const addResidentBtn = document.getElementById("addResident");
    const residentCountSpan = document.getElementById("resident-count");

    const childrenContainer = document.getElementById("children-container");
    const children = childrenContainer.querySelectorAll(".child");
    const addChildBtn = document.getElementById("addChild");
    const childCountSpan = document.getElementById("child-count");

    const rememberMe = document.getElementById("rememberMe");
    const ownerName = document.getElementById("ownerFirstName");
    const ownerPhone = document.getElementById("ownerPhone");
    const ownerEmail = document.getElementById("ownerEmail");
    const res1Name = document.getElementById("adult1FirstName");
    const res1Phone = document.getElementById("adult1Phone");
    const res1Email = document.getElementById("adult1Email");

    function updateResidentCounter() {
        const visibleResidents = Array.from(residents).filter(r => r.style.display === "flex");
        residentCountSpan.textContent = ` - бр. ${visibleResidents.length}`;
    }

    function updateChildCounter() {
        const visibleChildren = Array.from(children).filter(c => c.style.display === "flex");
        childCountSpan.textContent = ` - бр. ${visibleChildren.length}`;
    }

    function clearInputs(container) {
        container.querySelectorAll("input").forEach(input => input.value = "");
    }

    addResidentBtn.addEventListener("click", function () {
        const hiddenResident = Array.from(residents).find(r => r.style.display === "none");
        if (hiddenResident) {
            hiddenResident.style.display = "flex";
            updateResidentCounter();
        } else {
            alert("Достигнат е максималният брой обитатели.");
        }
    });

    addChildBtn.addEventListener("click", function () {
        const hiddenChild = Array.from(children).find(c => c.style.display === "none");
        if (hiddenChild) {
            hiddenChild.style.display = "flex";
            updateChildCounter();
        } else {
            alert("Достигнат е максималният брой деца.");
        }
    });

    rememberMe.addEventListener("change", function () {
        if (this.checked) {
            res1Name.value = ownerName.value;
            res1Phone.value = ownerPhone.value;
            res1Email.value = ownerEmail.value;

            const row = res1Name.closest(".resident");
            if (row && row.style.display === "none") {
                row.style.display = "flex";
                updateResidentCounter();
            }
        } else {
            res1Name.value = "";
            res1Phone.value = "";
            res1Email.value = "";
        }
    });

    [ownerName, ownerPhone, ownerEmail].forEach((ownerField, i) => {
        ownerField.addEventListener("input", () => {
            if (rememberMe.checked) {
                [res1Name, res1Phone, res1Email][i].value = ownerField.value;
            }
        });
    });

    [res1Name, res1Phone, res1Email].forEach((resField, i) => {
        resField.addEventListener("input", () => {
            if (rememberMe.checked && resField.value !== [ownerName, ownerPhone, ownerEmail][i].value) {
                rememberMe.checked = false;
            }
        });
    });

// Remove buttons for adults
    residents.forEach((resident, index) => {
        const removeBtn = resident.querySelector(".remove-btn");
        if (removeBtn) {
            removeBtn.addEventListener("click", () => {
                resident.style.display = "none";
                clearInputs(resident);
                updateResidentCounter();

                // Ако първият обитател бъде премахнат – премахваме чекбокса
                if (index === 0 && rememberMe.checked) {
                    rememberMe.checked = false;
                }
            });
        }
    });


    // Remove buttons for children
    children.forEach(child => {
        const removeBtn = child.querySelector(".remove-btn");
        if (removeBtn) {
            removeBtn.addEventListener("click", () => {
                child.style.display = "none";
                clearInputs(child);
                updateChildCounter();
            });
        }
    });
});