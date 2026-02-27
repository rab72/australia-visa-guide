import Link from "next/link";
import { notFound } from "next/navigation";
import visas from "@/data/visas";
import DocumentChecklist from "@/components/DocumentChecklist";
import PrintButton from "@/components/PrintButton";

export async function generateStaticParams() {
  return visas.map((v) => ({ id: v.id }));
}

export async function generateMetadata({ params }) {
  const p = await params;
  const visa = visas.find((v) => v.id === p.id);
  if (!visa) return {};
  return {
    title: `${visa.name} (${visa.subclass}) \u2013 Required Documents | VisaGuide.au`,
  };
}

export default async function VisaDetailPage({ params }) {
  const p = await params;
  const visa = visas.find((v) => v.id === p.id);
  if (!visa) notFound();

  return (
    <>
      <header className="site-header" style={{ padding: "1.5rem 0 2rem" }}>
        <div className="header-inner">
          <div className="header-top">
            <span className="flag">🇦🇺</span>
            <Link href="/" className="logo">
              Visa<span>Guide</span>.au
            </Link>
          </div>
        </div>
      </header>

      <main className="container">
        <Link href="/" className="back-btn">
          ← All Visa Categories
        </Link>

        {/* Detail Hero Card */}
        <div className="detail-hero">
          <div className="detail-hero-header">
            <span className="detail-icon">{visa.categoryIcon}</span>
            <div className="detail-titles">
              <div className="detail-badge">{visa.subclass}</div>
              <h1 className="detail-name">{visa.name}</h1>
              <span className="category-tag">{visa.category}</span>
            </div>
          </div>
          <p className="detail-desc">{visa.description}</p>

          <div className="detail-info-grid">
            <div className="info-card">
              <span className="info-label">Processing Time</span>
              <span className="info-value">{visa.processingTime}</span>
            </div>
            <div className="info-card">
              <span className="info-label">Visa Validity</span>
              <span className="info-value">{visa.validity}</span>
            </div>
            <div className="info-card">
              <span className="info-label">Application Fee</span>
              <span className="info-value">{visa.cost}</span>
            </div>
            <div className="info-card">
              <span className="info-label">Total Documents</span>
              <span className="info-value">{visa.documents.length} items</span>
            </div>
          </div>
        </div>

        {/* Content Grid */}
        <div className="content-grid">
          {/* LEFT: Document Checklist + Notes + Disclaimer */}
          <div>
            <DocumentChecklist documents={visa.documents} />

            {visa.notes.length > 0 && (
              <div className="card-section" style={{ marginTop: "1.5rem" }}>
                <h2 className="card-section-title">💡 Important Notes</h2>
                <ul className="notes-list">
                  {visa.notes.map((note, i) => (
                    <li key={i}>{note}</li>
                  ))}
                </ul>
              </div>
            )}

            <div className="disclaimer">
              <strong>Disclaimer:</strong> Document requirements may vary depending on your
              individual circumstances, country of origin, and the most recent policy updates from
              the Australian Department of Home Affairs. Always verify requirements at{" "}
              <a href="https://immi.homeaffairs.gov.au" target="_blank" rel="noopener">
                immi.homeaffairs.gov.au
              </a>{" "}
              before lodging your application.
            </div>
          </div>

          {/* RIGHT: Eligibility + Summary + Apply */}
          <div>
            <div className="card-section">
              <h2 className="card-section-title">✅ Eligibility Requirements</h2>
              <ul className="eligibility-list">
                {visa.eligibilityPoints.map((point, i) => (
                  <li key={i}>{point}</li>
                ))}
              </ul>
            </div>

            <div className="card-section" style={{ marginTop: "1.5rem" }}>
              <h2 className="card-section-title">📄 Document Summary</h2>
              <div style={{ display: "flex", flexDirection: "column", gap: "0.75rem" }}>
                <div
                  style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    padding: "0.75rem",
                    background: "rgba(239,68,68,0.06)",
                    borderRadius: "8px",
                    borderLeft: "3px solid var(--danger)",
                  }}
                >
                  <span style={{ fontSize: "0.9rem", fontWeight: 600, color: "var(--text)" }}>
                    Required Documents
                  </span>
                  <span style={{ fontSize: "1.1rem", fontWeight: 800, color: "var(--danger)" }}>
                    {visa.mandatoryCount}
                  </span>
                </div>
                <div
                  style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    padding: "0.75rem",
                    background: "rgba(100,116,139,0.06)",
                    borderRadius: "8px",
                    borderLeft: "3px solid var(--text-muted)",
                  }}
                >
                  <span style={{ fontSize: "0.9rem", fontWeight: 600, color: "var(--text)" }}>
                    Optional Documents
                  </span>
                  <span
                    style={{ fontSize: "1.1rem", fontWeight: 800, color: "var(--text-muted)" }}
                  >
                    {visa.optionalCount}
                  </span>
                </div>
                <div
                  style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    padding: "0.75rem",
                    background: "rgba(26,79,160,0.06)",
                    borderRadius: "8px",
                    borderLeft: "3px solid var(--primary)",
                  }}
                >
                  <span style={{ fontSize: "0.9rem", fontWeight: 600, color: "var(--text)" }}>
                    Total Documents
                  </span>
                  <span style={{ fontSize: "1.1rem", fontWeight: 800, color: "var(--primary)" }}>
                    {visa.documents.length}
                  </span>
                </div>
              </div>
            </div>

            <div className="card-section" style={{ marginTop: "1.5rem", textAlign: "center" }}>
              <h2 className="card-section-title" style={{ justifyContent: "center" }}>
                🌐 Ready to Apply?
              </h2>
              <p
                style={{
                  fontSize: "0.875rem",
                  color: "var(--text-muted)",
                  marginBottom: "1rem",
                  lineHeight: 1.6,
                }}
              >
                Submit your application online through the Australian Government&apos;s ImmiAccount
                portal.
              </p>
              <a
                href="https://immi.homeaffairs.gov.au/visas/getting-a-visa/visa-listing"
                target="_blank"
                rel="noopener"
                style={{
                  display: "inline-block",
                  padding: "0.85rem 1.5rem",
                  background: "var(--primary)",
                  color: "white",
                  borderRadius: "var(--radius-sm)",
                  fontWeight: 700,
                  fontSize: "0.9rem",
                  transition: "background 0.2s",
                }}
              >
                Apply on DOHA Website →
              </a>
            </div>

            <div style={{ marginTop: "1rem", textAlign: "center" }}>
              <PrintButton />
            </div>
          </div>
        </div>
      </main>
    </>
  );
}
