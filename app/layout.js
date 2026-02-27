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
          <p>
            <strong>VisaGuide.au</strong> &mdash; Your guide to Australian visa requirements.
            <br />
            Not affiliated with the Australian Government &middot; Data sourced from{" "}
            <a href="https://immi.homeaffairs.gov.au" target="_blank" rel="noopener">
              immi.homeaffairs.gov.au
            </a>{" "}
            &middot; Updated 2025&ndash;2026
            <br />
            Made by <strong>Rabin</strong>
          </p>
        </footer>
      </body>
    </html>
  );
}
