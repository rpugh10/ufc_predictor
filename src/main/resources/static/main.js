function showPopup(message, onClose) {
  popupMessage.innerText = message;
  popup.classList.remove("hidden");

  popupBtn.onclick = () => {
    popup.classList.add("hidden");
    if (onClose) onClose();
  };
}


document.addEventListener("DOMContentLoaded", () => {

  const form = document.getElementById("predictionForm");
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

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const fighterA = document.getElementById("fighterA").value;
    const fighterB = document.getElementById("fighterB").value;

    try {
      const response = await fetch("http://localhost:8080/predict", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ fighterA, fighterB })
      });

      if (!response.ok) {
        throw new Error("Prediction failed");
      }

      const result = await response.json();

      showPopup(
        `Winner: ${result.winner}\nConfidence: ${(result.confidence * 100).toFixed(1)}%`
      );

    } catch (err) {
      showPopup(err.message);
    }
  });
});