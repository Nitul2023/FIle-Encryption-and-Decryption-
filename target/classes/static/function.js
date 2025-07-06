// FEEDBACK FORM SUBMISSION HANDLER
document.addEventListener("DOMContentLoaded", () => {
  const feedbackForm = document.getElementById("feedback-form");

  if (feedbackForm) {
    feedbackForm.addEventListener("submit", async (e) => {
      e.preventDefault();

      const name = document.getElementById("name").value.trim();
      const email = document.getElementById("email").value.trim();
      const message = document.getElementById("message").value.trim();

      if (!name || !email || !message) {
        document.getElementById("feedback-response").textContent =
          "All fields are required!";
        return;
      }

      try {
        const response = await fetch("/submit-feedback", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ name, email, message }),
        });

        if (response.ok) {
          const text = await response.text();
          document.getElementById("feedback-response").textContent = text;
          feedbackForm.reset();
        } else {
          document.getElementById("feedback-response").textContent =
            "❌ Failed to submit feedback. Try again.";
        }
      } catch (error) {
        document.getElementById("feedback-response").textContent =
          "❌ Something went wrong. Try again later.";
        console.error("Error:", error);
      }
    });
  }
});
