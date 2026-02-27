import Link from "next/link";
import visas from "@/data/visas";

function getCategories(list) {
  const seen = new Set();
  const cats = [];
  for (const v of list) {
    if (!seen.has(v.category)) {
      seen.add(v.category);
      cats.push(v.category);
    }
  }
  return cats;
}

function groupByCategory(list) {
  const map = new Map();
  for (const v of list) {
    if (!map.has(v.category)) map.set(v.category, []);
    map.get(v.category).push(v);
  }
  return map;
}

export default async function Home({ searchParams }) {
  const params = await searchParams;
  const q = params?.q?.trim() ?? "";
  const activeCategory = params?.category ?? null;

  const allCategories = getCategories(visas);

  let filtered = visas;
  if (q) {
    const lq = q.toLowerCase();
    filtered = visas.filter(
      (v) =>
        v.name.toLowerCase().includes(lq) ||
        v.subclass.toLowerCase().includes(lq) ||
        v.category.toLowerCase().includes(lq) ||
        v.description.toLowerCase().includes(lq)
    );
  } else if (activeCategory) {
    filtered = visas.filter((v) => v.category === activeCategory);
  }

  const grouped = groupByCategory(filtered);
  const isDefault = !q && !activeCategory;

  return (
    <>
      <header className="site-header">
        <div className="header-inner">
          <div className="header-top">
            <span className="flag">🇦🇺</span>
            <Link href="/" className="logo">
              Visa<span>Guide</span>.au
            </Link>
          </div>
          <h1 className="hero-title">Australian Visa Document Checker</h1>
          <p className="hero-subtitle">
            Find out exactly which documents you need to apply for any Australian visa — all in one
            place.
          </p>
          <form action="/" method="get" className="search-box">
            <input
              type="text"
              name="q"
              className="search-input"
              placeholder="Search by visa name, subclass, or keyword..."
              defaultValue={q}
              autoComplete="off"
            />
            <button type="submit" className="search-btn">
              🔍 Search
            </button>
          </form>
        </div>
      </header>

      <main className="container">
        {/* Stats Bar */}
        <div className="stats-bar">
          <div className="stat">
            <strong>{visas.length}</strong> visa categories covered
          </div>
          <div className="stat">
            <strong>{allCategories.length}</strong> visa types
          </div>
          <div className="stat">
            Updated for <strong>2025–2026</strong>
          </div>
        </div>

        {/* Search Result Notice */}
        {q && (
          <div className="search-notice">
            Showing <strong>{filtered.length}</strong> results for &ldquo;{q}&rdquo;&nbsp;&middot;&nbsp;
            <Link href="/">Clear search</Link>
          </div>
        )}

        {/* Category Filter Tabs */}
        {!q && (
          <div className="category-tabs">
            <Link href="/" className={`tab-btn${!activeCategory ? " active" : ""}`}>
              All Visas
            </Link>
            {allCategories.map((cat) => (
              <Link
                key={cat}
                href={`/?category=${encodeURIComponent(cat)}`}
                className={`tab-btn${activeCategory === cat ? " active" : ""}`}
              >
                {cat}
              </Link>
            ))}
          </div>
        )}

        {/* No Results */}
        {filtered.length === 0 && (
          <div className="no-results">
            <div className="icon">🔍</div>
            <h3>No visas found</h3>
            <p>
              Try a different search term or <Link href="/">browse all visas</Link>.
            </p>
          </div>
        )}

        {/* Grouped Visa Sections (default view) */}
        {isDefault && filtered.length > 0 &&
          Array.from(grouped.entries()).map(([category, categoryVisas]) => (
            <div key={category}>
              <div className="section-title">
                <span className="icon">{categoryVisas[0].categoryIcon}</span>
                <span>{category}</span>
                <span style={{ fontSize: "0.85rem", fontWeight: 400, color: "var(--text-muted)" }}>
                  ({categoryVisas.length})
                </span>
              </div>
              <div className="visa-grid">
                {categoryVisas.map((visa) => (
                  <VisaCard key={visa.id} visa={visa} showDocCount />
                ))}
              </div>
            </div>
          ))}

        {/* Flat List (search / category filter view) */}
        {!isDefault && filtered.length > 0 && (
          <div className="visa-grid">
            {filtered.map((visa) => (
              <VisaCard key={visa.id} visa={visa} showCategory />
            ))}
          </div>
        )}

        {/* Disclaimer */}
        <div className="disclaimer">
          <strong>Disclaimer:</strong> This information is provided for general guidance purposes
          only and is current as of 2025–2026. Visa requirements can change at any time. Always
          verify the latest requirements directly with the{" "}
          <a href="https://immi.homeaffairs.gov.au" target="_blank" rel="noopener">
            Australian Department of Home Affairs
          </a>{" "}
          before submitting your application. This website is not affiliated with the Australian
          Government.
        </div>
      </main>
    </>
  );
}

function VisaCard({ visa, showDocCount, showCategory }) {
  return (
    <Link href={`/visa/${visa.id}`} className="visa-card" title={visa.name}>
      <div className="card-header">
        <span className="card-icon">{visa.categoryIcon}</span>
        <div className="card-titles">
          <span className="subclass-badge">{visa.subclass}</span>
          <div className="card-name">{visa.name}</div>
        </div>
      </div>

      <p className="card-desc">{visa.description}</p>

      <div className="card-meta">
        <div className="meta-item">
          <span className="meta-label">Processing Time</span>
          <span className="meta-value">{visa.processingTime}</span>
        </div>
        <div className="meta-item">
          <span className="meta-label">Validity</span>
          <span className="meta-value">{visa.validity}</span>
        </div>
        {showDocCount && (
          <>
            <div className="meta-item">
              <span className="meta-label">Application Fee</span>
              <span className="meta-value">{visa.cost}</span>
            </div>
            <div className="meta-item">
              <span className="meta-label">Documents</span>
              <span className="meta-value">{visa.documents.length} items</span>
            </div>
          </>
        )}
      </div>

      <div className="card-footer">
        {showDocCount && (
          <span className="doc-count">
            <strong>{visa.mandatoryCount}</strong> required, {visa.optionalCount} optional
          </span>
        )}
        {showCategory && <span className="category-tag">{visa.category}</span>}
        <span className="view-btn">View Checklist →</span>
      </div>
    </Link>
  );
}
