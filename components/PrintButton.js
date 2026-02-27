"use client";

export default function PrintButton() {
  return (
    <button
      onClick={() => window.print()}
      style={{
        padding: "0.65rem 1.25rem",
        background: "var(--surface)",
        border: "1px solid var(--border)",
        borderRadius: "var(--radius-sm)",
        fontSize: "0.875rem",
        fontWeight: 500,
        color: "var(--text-muted)",
        cursor: "pointer",
        width: "100%",
      }}
    >
      🖨 Print Checklist
    </button>
  );
}
