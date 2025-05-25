// Mobile navigation toggle
document.addEventListener("DOMContentLoaded", () => {
  const hamburger = document.getElementById("hamburger")
  const navMenu = document.getElementById("nav-menu")

  if (hamburger && navMenu) {
    hamburger.addEventListener("click", () => {
      hamburger.classList.toggle("active")
      navMenu.classList.toggle("active")
    })

    // Close menu when clicking on a link
    const navLinks = document.querySelectorAll(".nav-link")
    navLinks.forEach((link) => {
      link.addEventListener("click", () => {
        hamburger.classList.remove("active")
        navMenu.classList.remove("active")
      })
    })

    // Close menu when clicking outside
    document.addEventListener("click", (event) => {
      if (!hamburger.contains(event.target) && !navMenu.contains(event.target)) {
        hamburger.classList.remove("active")
        navMenu.classList.remove("active")
      }
    })
  }
})

// Smooth scrolling for anchor links
document.querySelectorAll('a[href^="#"]').forEach((anchor) => {
  anchor.addEventListener("click", function (e) {
    e.preventDefault()
    const target = document.querySelector(this.getAttribute("href"))
    if (target) {
      target.scrollIntoView({
        behavior: "smooth",
        block: "start",
      })
    }
  })
})

// Form validation helpers
function validateEmail(email) {
  const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  return re.test(email)
}

function validatePassword(password) {
  return password.length >= 6
}

function showNotification(message, type = "success") {
  // Create notification element
  const notification = document.createElement("div")
  notification.className = `notification notification-${type}`
  notification.innerHTML = `
        <span>${message}</span>
        <button class="notification-close">&times;</button>
    `

  // Add styles
  notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        background: ${type === "success" ? "#4caf50" : type === "error" ? "#f44336" : "#ffc107"};
        color: white;
        padding: 15px 20px;
        border-radius: 10px;
        box-shadow: 0 5px 15px rgba(0,0,0,0.2);
        z-index: 10000;
        display: flex;
        align-items: center;
        gap: 10px;
        max-width: 300px;
        animation: slideIn 0.3s ease;
    `

  // Add animation keyframes
  if (!document.querySelector("#notification-styles")) {
    const style = document.createElement("style")
    style.id = "notification-styles"
    style.textContent = `
            @keyframes slideIn {
                from { transform: translateX(100%); opacity: 0; }
                to { transform: translateX(0); opacity: 1; }
            }
            @keyframes slideOut {
                from { transform: translateX(0); opacity: 1; }
                to { transform: translateX(100%); opacity: 0; }
            }
            .notification-close {
                background: none;
                border: none;
                color: white;
                font-size: 18px;
                cursor: pointer;
                padding: 0;
                margin-left: auto;
            }
        `
    document.head.appendChild(style)
  }

  document.body.appendChild(notification)

  // Close button functionality
  const closeBtn = notification.querySelector(".notification-close")
  closeBtn.addEventListener("click", () => {
    notification.style.animation = "slideOut 0.3s ease"
    setTimeout(() => notification.remove(), 300)
  })

  // Auto remove after 5 seconds
  setTimeout(() => {
    if (notification.parentNode) {
      notification.style.animation = "slideOut 0.3s ease"
      setTimeout(() => notification.remove(), 300)
    }
  }, 5000)
}

// Loading state helper
function setLoadingState(button, isLoading, originalText) {
  if (isLoading) {
    button.disabled = true
    button.innerHTML = "⏳ Đang xử lý..."
    button.style.opacity = "0.7"
  } else {
    button.disabled = false
    button.innerHTML = originalText
    button.style.opacity = "1"
  }
}

// Local storage helpers
function saveToLocalStorage(key, data) {
  try {
    localStorage.setItem(key, JSON.stringify(data))
  } catch (error) {
    console.error("Error saving to localStorage:", error)
  }
}

function getFromLocalStorage(key) {
  try {
    const data = localStorage.getItem(key)
    return data ? JSON.parse(data) : null
  } catch (error) {
    console.error("Error reading from localStorage:", error)
    return null
  }
}

// Format date helper
function formatDate(dateString) {
  const date = new Date(dateString)
  return date.toLocaleDateString("vi-VN", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
  })
}

// Format file size helper
function formatFileSize(bytes) {
  if (bytes === 0) return "0 Bytes"
  const k = 1024
  const sizes = ["Bytes", "KB", "MB", "GB"]
  const i = Math.floor(Math.log(bytes) / Math.log(k))
  return Number.parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + " " + sizes[i]
}

// Debounce helper
function debounce(func, wait) {
  let timeout
  return function executedFunction(...args) {
    const later = () => {
      clearTimeout(timeout)
      func(...args)
    }
    clearTimeout(timeout)
    timeout = setTimeout(later, wait)
  }
}

// Initialize tooltips (if needed)
function initTooltips() {
  const tooltipElements = document.querySelectorAll("[data-tooltip]")
  tooltipElements.forEach((element) => {
    element.addEventListener("mouseenter", function () {
      const tooltip = document.createElement("div")
      tooltip.className = "tooltip"
      tooltip.textContent = this.getAttribute("data-tooltip")
      tooltip.style.cssText = `
                position: absolute;
                background: #333;
                color: white;
                padding: 8px 12px;
                border-radius: 6px;
                font-size: 0.8rem;
                z-index: 10000;
                pointer-events: none;
                white-space: nowrap;
            `
      document.body.appendChild(tooltip)

      const rect = this.getBoundingClientRect()
      tooltip.style.left = rect.left + rect.width / 2 - tooltip.offsetWidth / 2 + "px"
      tooltip.style.top = rect.top - tooltip.offsetHeight - 8 + "px"

      this._tooltip = tooltip
    })

    element.addEventListener("mouseleave", function () {
      if (this._tooltip) {
        this._tooltip.remove()
        this._tooltip = null
      }
    })
  })
}

// Initialize page
document.addEventListener("DOMContentLoaded", () => {
  initTooltips()

  // Add loading animation to all forms
  const forms = document.querySelectorAll("form")
  forms.forEach((form) => {
    form.addEventListener("submit", (e) => {
      const submitBtn = form.querySelector('button[type="submit"]')
      if (submitBtn && !submitBtn.disabled) {
        const originalText = submitBtn.innerHTML
        setLoadingState(submitBtn, true, originalText)

        // Reset after 3 seconds if not handled by specific form handler
        setTimeout(() => {
          if (submitBtn.disabled) {
            setLoadingState(submitBtn, false, originalText)
          }
        }, 3000)
      }
    })
  })
})

// Export functions for use in other scripts
window.EduCheck = {
  validateEmail,
  validatePassword,
  showNotification,
  setLoadingState,
  saveToLocalStorage,
  getFromLocalStorage,
  formatDate,
  formatFileSize,
  debounce,
}
