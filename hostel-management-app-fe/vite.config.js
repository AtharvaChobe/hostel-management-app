import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(), tailwindcss()],
  theme: {
    extend: {
      colors: {
        primary: '#1E40AF',
        secondary: '#FBBF24',
        background: '#F9FAFB',
      },
      fontSize: {
        title: '5rem',
        subtitle: '1.25rem',
      }
    }
  },
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
})
