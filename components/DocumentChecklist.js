"use client";

import { useState } from "react";

export default function DocumentChecklist({ documents }) {
  const [checked, setChecked] = useState(() => new Array(documents.length).fill(false));

  function toggle(index) {
    setChecked((prev) => {
      const next = [...prev];
      next[index] = !next[index];
      return next;
    });
  }

  const checkedCount = checked.filter(Boolean).length;
  const total = documents.length;
  const pct = total > 0 ? Math.round((checkedCount / total) * 100) : 0;

  return (
    <div className="card-section">
      <h2 className="card-section-title">📋 Required Documents Checklist</h2>

      {/* Progress Bar */}
      <div className="progress-wrap">
        <div className="progress-header">
          <span className="progress-label">Documents Gathered</span>
          <span className="progress-count">
            {checkedCount} / {total}
          </span>
        </div>
        <div className="progress-bar">
          <div className="progress-fill" style={{ width: `${pct}%` }} />
        </div>
      </div>

      {/* Document Items */}
      <div className="doc-list">
        {documents.map((doc, i) => (
          <div key={i} className="doc-item">
            <div
              className={`doc-checkbox${checked[i] ? " checked" : ""}`}
              onClick={() => toggle(i)}
              title="Mark as gathered"
            >
              {checked[i] && "✓"}
            </div>
            <div className="doc-info">
              <div className="doc-name">
                <span>{doc.name}</span>
                {doc.mandatory ? (
                  <span className="badge-mandatory">Required</span>
                ) : (
                  <span className="badge-optional">Optional</span>
                )}
              </div>
              <div className="doc-desc">{doc.description}</div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
