const popup = document.getElementById("popup");
const popupMessage = document.getElementById("popupMessage");
const popupBtn = document.getElementById("popupBtn");

function showPopup(message, onClose) {
  popupMessage.innerText = message;
  popup.classList.remove("hidden");

  popupBtn.onclick = () => {
    popup.classList.add("hidden");
    if (onClose) onClose();
  };
}


document.addEventListener("DOMContentLoaded", () => {
    console.log("createAccount.js loaded");

  const form = document.getElementById("createUser");
  const message = document.getElementById("message");

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const confirmPassword =
      document.getElementById("confirmPassword").value;

    // ✅ Client-side validation
    if (password !== confirmPassword) {
      showPopup("Passwords do not match")
      return;
    }

    const payload = {
      username: username,
      password: password,
      confirmPassword: confirmPassword
    };

    try {
      const response = await fetch("http://localhost:8080/users/create", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
      });

      if (!response.ok) {
        const error = await response.text();
        throw new Error(error || "Account creation failed");
      }

    showPopup("Account created successfully!", () => {
        window.location.href = "login.html";
    });

    } catch (err) {
     showPopup(err.message || "Something went wrong");
    }
  });

});