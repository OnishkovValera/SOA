import path from "path"
import tailwindcss from "@tailwindcss/vite"
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
    base: "./",
    plugins: [react(), tailwindcss()],
    build: {
        assetsDir: '.',      // ассеты будут в той же папке что и index.html
        rollupOptions: {
            output: {
                assetFileNames: '[name].[ext]'  // сохраняем оригинальные имена файлов
            }
        }
    },
    resolve: {
        alias: {
            "@": path.resolve(__dirname, "./src"),
        },
    },
})
