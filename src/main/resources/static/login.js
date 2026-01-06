function login(){
    window.location.href = "main.html";
}

function createAccount(){
    window.location.href = "createAccount.html"
}

function showPopup(message, onClose) {
  popupMessage.innerText = message;
  popup.classList.remove("hidden");

  popupBtn.onclick = () => {
    popup.classList.add("hidden");
    if (onClose) onClose();
  };
}

document.addEventListener("DOMContentLoaded", () => {

  const form = document.getElementById("loginForm");

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
      const response = await fetch("http://localhost:8080/users/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
      });

      if (!response.ok) {
        const error = await response.text();
        throw new Error(error || "Invalid credentials");
      }

      showPopup("Login successful!", () => {
        window.location.href = "main.html";
      });

    } catch (err) {
      showPopup(err.message);
    }
  });
});