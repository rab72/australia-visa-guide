import Link from "next/link";
import "./globals.css";

export const metadata = {
  title: "Australia Visa Guide \u2013 Required Documents Checker",
  description:
    "Find out exactly which documents you need for every Australian visa category. Student, Skilled Worker, Visitor, Partner, Working Holiday and more.",
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <head>
        <link
          rel="icon"
          href="data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100'><text y='.9em' font-size='90'>🇦🇺</text></svg>"
        />
      </head>
      <body>
        {children}
        <footer className="site-footer">
          <div className="footer-inner">
            <div className="footer-grid">

              {/* Brand */}
              <div className="footer-brand">
                <div className="footer-logo">
                  <span>🇦🇺</span>
                  <span>Visa<strong>Guide</strong></span>
                </div>
                <p className="footer-tagline">
                  Your complete guide to Australian visa document requirements. Free, up-to-date, and easy to use.
                </p>
              </div>

              {/* Visa Categories */}
              <div className="footer-col">
                <h4>Visa Categories</h4>
                <ul>
                  <li><Link href="/?category=Student">Student</Link></li>
                  <li><Link href="/?category=Skilled+Worker">Skilled Worker</Link></li>
                  <li><Link href="/?category=Visitor">Visitor</Link></li>
                  <li><Link href="/?category=Partner+%26+Family">Partner &amp; Family</Link></li>
                  <li><Link href="/?category=Working+Holiday">Working Holiday</Link></li>
                  <li><Link href="/?category=Humanitarian">Humanitarian</Link></li>
                </ul>
              </div>

              {/* Resources */}
              <div className="footer-col">
                <h4>Resources</h4>
                <ul>
                  <li>
                    <a href="https://immi.homeaffairs.gov.au" target="_blank" rel="noopener">
                      Dept. of Home Affairs
                    </a>
                  </li>
                  <li>
                    <a href="https://immi.homeaffairs.gov.au/visas/getting-a-visa/visa-listing" target="_blank" rel="noopener">
                      Visa Listing
                    </a>
                  </li>
                  <li>
                    <a href="https://online.immi.gov.au/lusc/login" target="_blank" rel="noopener">
                      ImmiAccount Login
                    </a>
                  </li>
                </ul>
              </div>

            </div>

            {/* Bottom bar */}
            <div className="footer-bottom">
              <span>© 2025–2026 <strong>VisaGuide</strong></span>
              <span>Not affiliated with the Australian Government</span>
              <span>Made with ♥ by <strong>Rabin</strong></span>
            </div>
          </div>
        </footer>
      </body>
    </html>
  );
}
