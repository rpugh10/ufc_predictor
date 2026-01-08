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

  // ✅ AUTOCOMPLETE SETUP (inside DOMContentLoaded)
  const fighterList = document.getElementById("fighterList");
  const fighterAInput = document.getElementById("fighterA");
  const fighterBInput = document.getElementById("fighterB");

  let debounceTimer = null;

  async function fetchMatches(query) {
    const res = await fetch(`/fighters/search?q=${encodeURIComponent(query)}`);
    if (!res.ok) return [];
    const data = await res.json();
    return data.matches || [];
  }

  async function updateDatalist(query) {
    const matches = await fetchMatches(query);

    fighterList.innerHTML = "";
    matches.forEach((name) => {
      const opt = document.createElement("option");
      opt.value = name;
      fighterList.appendChild(opt);
    });
  }

  function setupAutocomplete(inputEl) {
    inputEl.addEventListener("input", () => {
      const q = inputEl.value.trim().toLowerCase();
      clearTimeout(debounceTimer);

      if (q.length < 2) {
        fighterList.innerHTML = "";
        return;
      }

      debounceTimer = setTimeout(() => updateDatalist(q), 200);
    });
  }

  setupAutocomplete(fighterAInput);
  setupAutocomplete(fighterBInput);

  // ✅ PREDICT SUBMIT
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const fighterA = fighterAInput.value.trim();
    const fighterB = fighterBInput.value.trim();

    if (!fighterA || !fighterB) {
      showPopup("Please enter both fighter names.");
      return;
    }

    try {
      const response = await fetch("http://127.0.0.1:8080/predict", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ fighterA, fighterB }),
      });

      if (!response.ok) {
        const errText = await response.text();
        throw new Error(errText || "Prediction failed");
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