/** @type {import("prettier").Config} */
const config = {
    tabWidth: 4,
    trailingComma: "none",
    printWidth: 100,
    semi: false,
    plugins: ["prettier-plugin-svelte"],
    overrides: [{ files: "*.svelte", options: { parser: "svelte" } }]
}

export default config
