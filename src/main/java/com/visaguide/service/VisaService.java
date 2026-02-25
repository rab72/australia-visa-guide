package com.visaguide.service;

import com.visaguide.model.DocumentItem;
import com.visaguide.model.VisaCategory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VisaService {

    private final List<VisaCategory> visaCategories;

    public VisaService() {
        this.visaCategories = buildVisaData();
    }

    public List<VisaCategory> getAllVisas() {
        return visaCategories;
    }

    public Optional<VisaCategory> getVisaById(String id) {
        return visaCategories.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    public Map<String, List<VisaCategory>> getVisasByCategory() {
        return visaCategories.stream().collect(Collectors.groupingBy(VisaCategory::getCategory));
    }

    public List<VisaCategory> searchVisas(String query) {
        if (query == null || query.isBlank()) return visaCategories;
        String q = query.toLowerCase();
        return visaCategories.stream()
                .filter(v -> v.getName().toLowerCase().contains(q)
                        || v.getSubclass().toLowerCase().contains(q)
                        || v.getCategory().toLowerCase().contains(q)
                        || v.getDescription().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    private List<VisaCategory> buildVisaData() {
        List<VisaCategory> list = new ArrayList<>();

        // ===================== STUDENT VISAS =====================
        list.add(new VisaCategory(
                "student-500", "Subclass 500", "Student Visa", "Student",
                "&#127979;",
                "Allows international students to study full-time in a registered course in Australia.",
                "4 weeks – 4 months", "Duration of course + 2 months", "AUD 710",
                List.of(
                        new DocumentItem("Valid Passport", "Must be valid for the entire duration of your stay in Australia.", true),
                        new DocumentItem("Confirmation of Enrolment (CoE)", "Issued by your registered Australian education provider.", true),
                        new DocumentItem("Genuine Temporary Entrant (GTE) Statement", "A written statement explaining your intention to stay temporarily.", true),
                        new DocumentItem("English Proficiency Evidence", "IELTS, TOEFL, PTE, or Cambridge result meeting minimum requirements.", true),
                        new DocumentItem("Financial Evidence", "Bank statements, scholarship letters, or sponsor letters showing sufficient funds.", true),
                        new DocumentItem("Health Insurance (OSHC)", "Overseas Student Health Cover from an approved provider.", true),
                        new DocumentItem("Health Examination Results", "HAP ID or upfront medical examination results.", true),
                        new DocumentItem("National Police Clearance", "Police clearance certificate from your home country.", true),
                        new DocumentItem("Passport-sized Photos", "Recent passport-sized photographs meeting Australian specifications.", true),
                        new DocumentItem("Academic Transcripts", "Previous educational qualifications and transcripts.", false),
                        new DocumentItem("Sponsor/Guardian Letter", "Required if you are under 18 years of age.", false),
                        new DocumentItem("NATI Declaration", "No tuition-related debt declaration if applicable.", false)
                ),
                List.of(
                        "Enrolled in a registered full-time course (CRICOS registered)",
                        "Meet health and character requirements",
                        "Genuine intention to study and leave after studies",
                        "Sufficient funds to cover tuition and living expenses"
                ),
                List.of(
                        "You may bring dependent family members on a Student visa",
                        "Work rights: up to 48 hours per fortnight during study periods",
                        "Apply online via ImmiAccount on the Department of Home Affairs website"
                )
        ));

        list.add(new VisaCategory(
                "student-guardian-590", "Subclass 590", "Student Guardian Visa", "Student",
                "&#127979;",
                "Allows a parent or eligible relative to stay in Australia to support a student visa holder under 18.",
                "4–8 weeks", "Duration of student's visa", "AUD 710",
                List.of(
                        new DocumentItem("Valid Passport", "Must be valid for the duration of stay.", true),
                        new DocumentItem("Proof of Relationship to Student", "Birth certificate or legal guardianship documents.", true),
                        new DocumentItem("Student's CoE and Visa Copy", "Copy of student's Confirmation of Enrolment and visa.", true),
                        new DocumentItem("Financial Evidence", "Evidence of funds to cover costs without working in Australia.", true),
                        new DocumentItem("Health Examination Results", "Completed medical examination via BUPA or panel doctor.", true),
                        new DocumentItem("National Police Clearance", "Character certificate from country of residence.", true),
                        new DocumentItem("School Welfare Arrangement Letter", "From the education provider if applicable.", false),
                        new DocumentItem("Accommodation Evidence", "Proof of arranged accommodation in Australia.", false)
                ),
                List.of(
                        "Must be parent, relative or eligible non-relative guardian",
                        "Student must be under 18 and hold a Student visa (subclass 500)",
                        "Cannot hold another substantive visa at the same time"
                ),
                List.of(
                        "No work rights on this visa",
                        "Must not leave the student without appropriate care arrangements"
                )
        ));

        // ===================== SKILLED / WORK VISAS =====================
        list.add(new VisaCategory(
                "tss-482", "Subclass 482", "Temporary Skill Shortage (TSS) Visa", "Skilled Worker",
                "&#128188;",
                "Allows Australian employers to address labour shortages by bringing in skilled workers from overseas.",
                "3–9 months", "Up to 4 years (depends on stream)", "AUD 3,035",
                List.of(
                        new DocumentItem("Valid Passport", "Passport valid for the duration of stay.", true),
                        new DocumentItem("Employer Sponsorship Approval", "Your employer must be an approved sponsor. Nomination letter required.", true),
                        new DocumentItem("Skills Assessment (if required)", "Relevant skills assessment from the relevant assessing authority.", true),
                        new DocumentItem("Employment Contract / Letter of Offer", "Signed contract or offer letter from the sponsoring employer.", true),
                        new DocumentItem("Qualifications & Academic Transcripts", "Degrees, diplomas, or trade certificates relevant to the occupation.", true),
                        new DocumentItem("Work Experience Evidence", "Reference letters, payslips, or employment records for at least 2 years.", true),
                        new DocumentItem("English Proficiency Evidence", "IELTS, TOEFL, or PTE result. Minimum Competent English.", true),
                        new DocumentItem("Health Examination Results", "Medical assessment by an approved panel doctor.", true),
                        new DocumentItem("National Police Clearance", "From all countries lived in for 12+ months in the past 10 years.", true),
                        new DocumentItem("Passport-sized Photos", "Recent photos meeting specifications.", true),
                        new DocumentItem("ANZSCO Occupation Match Evidence", "Evidence your role matches the nominated ANZSCO occupation.", true),
                        new DocumentItem("Training Levy Payment Receipt", "Skilling Australians Fund levy paid by employer.", false),
                        new DocumentItem("Registration or Licensing Evidence", "If your occupation requires registration (e.g., nurse, engineer).", false)
                ),
                List.of(
                        "Must be nominated by an approved Australian employer",
                        "Occupation must be on the relevant skilled occupation list",
                        "Meet minimum English language requirements",
                        "At least 2 years of relevant work experience"
                ),
                List.of(
                        "Two streams: Short-term (STSOL) up to 2 years, Medium-term (MLTSSL) up to 4 years",
                        "Can lead to permanent residence via subclass 186",
                        "Family members can be included in the application"
                )
        ));

        list.add(new VisaCategory(
                "ens-186", "Subclass 186", "Employer Nomination Scheme (ENS) Visa", "Skilled Worker",
                "&#128188;",
                "Permanent visa for skilled workers who are nominated by their employer.",
                "5 months – 2 years", "Permanent", "AUD 4,770",
                List.of(
                        new DocumentItem("Valid Passport", "Current passport.", true),
                        new DocumentItem("Employer Nomination Application", "Employer must first lodge a nomination for your position.", true),
                        new DocumentItem("Skills Assessment Certificate", "Positive skills assessment from the relevant authority.", true),
                        new DocumentItem("Employment Contract", "Signed contract for the nominated full-time position.", true),
                        new DocumentItem("Qualifications & Transcripts", "All relevant degrees and academic documents.", true),
                        new DocumentItem("Work Experience Evidence", "At least 3 years of relevant work experience.", true),
                        new DocumentItem("English Proficiency Evidence", "Minimum Competent English (IELTS 6.0 or equivalent).", true),
                        new DocumentItem("Health Examination Results", "Full medical and chest x-ray via approved panel doctor.", true),
                        new DocumentItem("National Police Clearance", "Police certificates from all countries of residence.", true),
                        new DocumentItem("Occupation Registration Evidence", "Professional registration if required for the role.", false),
                        new DocumentItem("Superannuation Details", "If transitioning from a 482 visa under TRT stream.", false)
                ),
                List.of(
                        "Three streams: Direct Entry, Temporary Residence Transition (TRT), or Labour Agreement",
                        "Employer must be approved and the position genuine",
                        "Applicant must be under 45 years of age (Direct Entry stream)",
                        "At least 3 years relevant work experience"
                ),
                List.of(
                        "Grants permanent residency",
                        "Eligible to apply for Australian citizenship after meeting residency requirements",
                        "Spouse and dependent children can be included"
                )
        ));

        list.add(new VisaCategory(
                "skilled-independent-189", "Subclass 189", "Skilled Independent Visa", "Skilled Worker",
                "&#128188;",
                "Points-tested permanent visa for skilled workers who are not sponsored by an employer, state, or family member.",
                "6–12 months", "Permanent", "AUD 4,640",
                List.of(
                        new DocumentItem("Valid Passport", "Valid passport for all applicants.", true),
                        new DocumentItem("Expression of Interest (SkillSelect)", "Submitted EOI through SkillSelect system and received invitation.", true),
                        new DocumentItem("Skills Assessment Certificate", "Positive assessment from the relevant assessing authority.", true),
                        new DocumentItem("English Proficiency Evidence", "Minimum Proficient English (IELTS 7.0 or equivalent).", true),
                        new DocumentItem("Work Experience Evidence", "Evidence of relevant employment history.", true),
                        new DocumentItem("Qualifications & Transcripts", "All tertiary qualifications with certified translations.", true),
                        new DocumentItem("Health Examination Results", "Medical examination from a panel doctor.", true),
                        new DocumentItem("National Police Clearance", "From all countries lived in for 12+ months.", true),
                        new DocumentItem("Points Test Evidence", "Documents supporting all claimed points (age, qualifications, experience).", true),
                        new DocumentItem("Statutory Declarations", "If unable to obtain formal reference letters.", false),
                        new DocumentItem("NAATI Certified Translations", "For all documents not in English.", false)
                ),
                List.of(
                        "Minimum 65 points on the points test",
                        "Age under 45 at time of invitation",
                        "Occupation on the Medium and Long-term Strategic Skills List (MLTSSL)",
                        "Positive skills assessment before submitting EOI"
                ),
                List.of(
                        "No employer or state sponsorship needed",
                        "Points are awarded for age, English, qualifications, and work experience",
                        "Invitation rounds happen regularly through SkillSelect"
                )
        ));

        list.add(new VisaCategory(
                "skilled-nominated-190", "Subclass 190", "Skilled Nominated Visa", "Skilled Worker",
                "&#128188;",
                "Permanent visa for skilled workers nominated by an Australian state or territory government.",
                "6–12 months", "Permanent", "AUD 4,640",
                List.of(
                        new DocumentItem("Valid Passport", "Valid passport.", true),
                        new DocumentItem("State/Territory Nomination Letter", "Nomination from the state/territory government.", true),
                        new DocumentItem("Expression of Interest (SkillSelect)", "EOI lodged in SkillSelect prior to nomination.", true),
                        new DocumentItem("Skills Assessment Certificate", "Positive skills assessment from relevant authority.", true),
                        new DocumentItem("English Proficiency Evidence", "Minimum Proficient English (IELTS 7.0 or equivalent).", true),
                        new DocumentItem("Work Experience Evidence", "Relevant employment records.", true),
                        new DocumentItem("Qualifications & Transcripts", "All relevant academic documents.", true),
                        new DocumentItem("Health Examination Results", "Panel doctor medical examination.", true),
                        new DocumentItem("National Police Clearance", "Police certificates from countries of residence.", true),
                        new DocumentItem("State Residency Commitment Evidence", "Evidence you intend to live and work in the nominating state.", false)
                ),
                List.of(
                        "Minimum 65 points (state nomination adds 5 bonus points)",
                        "Meet state/territory-specific requirements",
                        "Occupation must be on the state's nomination list",
                        "Age under 45 at time of invitation"
                ),
                List.of(
                        "Must commit to living in the nominating state for at least 2 years",
                        "Each state/territory has different occupation lists and requirements",
                        "State nomination gives you 5 extra points in SkillSelect"
                )
        ));

        // ===================== VISITOR VISAS =====================
        list.add(new VisaCategory(
                "visitor-600", "Subclass 600", "Visitor Visa", "Visitor",
                "&#9992;",
                "Allows tourists, family visitors, and business visitors to visit Australia temporarily.",
                "20 days – 3 months", "Up to 12 months", "AUD 190",
                List.of(
                        new DocumentItem("Valid Passport", "Passport with at least 6 months validity beyond intended stay.", true),
                        new DocumentItem("Completed Visa Application", "Online application via ImmiAccount.", true),
                        new DocumentItem("Financial Evidence", "Bank statements showing sufficient funds for the trip.", true),
                        new DocumentItem("Return Flight Evidence", "Confirmed return or onward ticket.", true),
                        new DocumentItem("Purpose of Visit Statement", "Brief explanation of why you are visiting Australia.", true),
                        new DocumentItem("Passport-sized Photos", "Recent photos meeting specifications.", true),
                        new DocumentItem("Health Insurance Evidence", "Strongly recommended; required for some nationalities.", false),
                        new DocumentItem("Invitation Letter (if visiting family/friends)", "Letter from Australian host with their visa/citizenship details.", false),
                        new DocumentItem("Health Examination Results", "May be required depending on country of origin or length of stay.", false),
                        new DocumentItem("Employment Evidence", "Payslips or employer letter showing employment to return to.", false),
                        new DocumentItem("Property/Assets Evidence", "Evidence of ties to home country (property, business).", false)
                ),
                List.of(
                        "Must not intend to work or study (except registered short courses)",
                        "Genuine visitor with intention to depart before visa expires",
                        "No serious criminal convictions",
                        "Meet health requirements"
                ),
                List.of(
                        "Three streams: Tourist, Sponsored Family, Business Visitor",
                        "Cannot extend tourist visa into a work visa while in Australia",
                        "ETA (subclass 601) available for eligible passport holders as a simpler alternative"
                )
        ));

        list.add(new VisaCategory(
                "eta-601", "Subclass 601", "Electronic Travel Authority (ETA)", "Visitor",
                "&#9992;",
                "An electronic visa linked to your passport for tourism or business purposes, available to eligible passport holders.",
                "Immediate – 24 hours", "12 months (multiple entry)", "AUD 20 service charge",
                List.of(
                        new DocumentItem("Eligible Passport", "Passport from an ETA-eligible country (e.g., USA, UK, Canada, Japan, Singapore, South Korea).", true),
                        new DocumentItem("Valid Email Address", "For ETA confirmation and correspondence.", true),
                        new DocumentItem("Credit/Debit Card", "For service charge payment via the Australia ETA app or travel agent.", true),
                        new DocumentItem("Return/Onward Ticket", "Evidence of intended departure from Australia.", false),
                        new DocumentItem("Financial Evidence", "Proof of sufficient funds, if requested.", false)
                ),
                List.of(
                        "Must hold an ETA-eligible passport",
                        "No intention to work or study long-term",
                        "Meet health and character requirements"
                ),
                List.of(
                        "Apply via the Australia ETA app (Apple/Google) or through a travel agent",
                        "Allows stays of up to 3 months per visit over 12 months",
                        "Much faster and cheaper than the subclass 600 Visitor Visa"
                )
        ));

        // ===================== PARTNER / FAMILY VISAS =====================
        list.add(new VisaCategory(
                "partner-offshore-309", "Subclass 309/100", "Partner Visa (Offshore)", "Partner & Family",
                "&#128149;",
                "For partners (married or de facto) of Australian citizens, permanent residents, or eligible New Zealand citizens applying from outside Australia.",
                "12–24 months (309); further 3–4 years (100)", "Temporary then Permanent", "AUD 8,850",
                List.of(
                        new DocumentItem("Valid Passport", "For both applicant and sponsor.", true),
                        new DocumentItem("Sponsor's Citizenship/PR Evidence", "Australian passport, citizenship certificate, or PR visa.", true),
                        new DocumentItem("Proof of Genuine Relationship", "Joint bank account statements, lease agreements, travel records, photos, statutory declarations.", true),
                        new DocumentItem("Marriage Certificate (if married)", "Official marriage certificate with certified translation if not in English.", true),
                        new DocumentItem("De Facto Evidence (if applicable)", "Cohabitation evidence for at least 12 months.", true),
                        new DocumentItem("Form 888 – Sponsor Declaration", "Statutory declaration by sponsor and two supporting persons.", true),
                        new DocumentItem("Birth Certificates", "For both applicant and sponsor.", true),
                        new DocumentItem("Health Examination Results", "Full medical examination including chest x-ray.", true),
                        new DocumentItem("National Police Clearance", "From all countries lived in for 12+ months in last 10 years.", true),
                        new DocumentItem("Passport-sized Photos", "Recent photographs.", true),
                        new DocumentItem("Relationship History Statement", "Detailed written statement of how the relationship developed.", false),
                        new DocumentItem("Evidence of Communication", "Text messages, emails, call logs showing ongoing communication.", false),
                        new DocumentItem("Previous Visa History", "Evidence of all previous Australian and overseas visas.", false)
                ),
                List.of(
                        "Must be in a genuine relationship (married or de facto) with an eligible Australian sponsor",
                        "Sponsor must be an Australian citizen, PR, or eligible NZ citizen",
                        "Must apply from outside Australia for this offshore stream",
                        "De facto couples must have lived together for at least 12 months"
                ),
                List.of(
                        "309 is a temporary visa (2-year wait) before 100 permanent visa is granted",
                        "Sponsor must lodge a sponsorship application simultaneously",
                        "Department assesses genuine relationship based on financial, social, commitment, and nature of household"
                )
        ));

        list.add(new VisaCategory(
                "partner-onshore-820", "Subclass 820/801", "Partner Visa (Onshore)", "Partner & Family",
                "&#128149;",
                "For partners of Australian citizens, permanent residents, or eligible NZ citizens who are already in Australia.",
                "12–24 months (820); further 3–4 years (801)", "Temporary then Permanent", "AUD 8,850",
                List.of(
                        new DocumentItem("Valid Passport", "Current passport for applicant and sponsor.", true),
                        new DocumentItem("Sponsor's Citizenship/PR Evidence", "Proof sponsor is an eligible Australian.", true),
                        new DocumentItem("Proof of Genuine Relationship", "Joint documents: bank accounts, lease, bills, photos, letters.", true),
                        new DocumentItem("Marriage Certificate or De Facto Evidence", "Official certificate or cohabitation evidence.", true),
                        new DocumentItem("Form 888 – Sponsor Declaration", "Two supporting declarations from people who know the couple.", true),
                        new DocumentItem("Birth Certificates", "For both parties.", true),
                        new DocumentItem("Health Examination Results", "Chest x-ray and medical examination.", true),
                        new DocumentItem("National Police Clearance", "From all countries resided in for 12+ months.", true),
                        new DocumentItem("Bridging Visa Continuation", "820 applicants receive a Bridging Visa A to remain lawfully in Australia.", false),
                        new DocumentItem("Previous Australian Visa Evidence", "Copy of current or last held Australian visa.", false)
                ),
                List.of(
                        "Must already be in Australia on a substantive visa when applying",
                        "Must be in a genuine relationship with an eligible Australian sponsor",
                        "Cannot be on a Bridging Visa when first applying (must hold substantive visa)"
                ),
                List.of(
                        "820 grants temporary residence while waiting for 801 permanent visa",
                        "Automatically upgraded to 801 permanent visa after 2 years",
                        "Work rights granted from the day of application"
                )
        ));

        list.add(new VisaCategory(
                "child-101", "Subclass 101", "Child Visa", "Partner & Family",
                "&#128149;",
                "Permanent visa for children of Australian citizens, permanent residents, or eligible NZ citizens to live in Australia.",
                "12–24 months", "Permanent", "AUD 2,615",
                List.of(
                        new DocumentItem("Valid Passport", "Child's current passport.", true),
                        new DocumentItem("Parent's Citizenship/PR Evidence", "Australian passport or PR visa of the sponsoring parent.", true),
                        new DocumentItem("Child's Birth Certificate", "Official birth certificate linking child to sponsoring parent.", true),
                        new DocumentItem("Custody/Consent Documents", "Court order or written consent from the other parent if required.", true),
                        new DocumentItem("Health Examination Results", "Medical exam for the child.", true),
                        new DocumentItem("National Police Clearance", "If child is 16 years or older.", false),
                        new DocumentItem("Adoption Papers", "If child is adopted.", false),
                        new DocumentItem("Legal Guardianship Papers", "If applicable.", false)
                ),
                List.of(
                        "Child must be under 18, or 18-25 and enrolled in full-time study, or over 18 with a disability",
                        "Sponsoring parent must be an Australian citizen, PR, or eligible NZ citizen",
                        "If child is over 18, additional requirements apply"
                ),
                List.of(
                        "Grants permanent residency to the child",
                        "Child can then apply for Australian citizenship",
                        "Subclass 102 is available if child is already in Australia (onshore)"
                )
        ));

        // ===================== WORKING HOLIDAY VISAS =====================
        list.add(new VisaCategory(
                "working-holiday-417", "Subclass 417", "Working Holiday Visa", "Working Holiday",
                "&#127774;",
                "Allows young adults from eligible countries to have an extended holiday and work in Australia.",
                "Several days – 4 weeks", "12 months", "AUD 635",
                List.of(
                        new DocumentItem("Valid Passport", "From an eligible passport country (e.g., UK, Germany, France, Italy, Netherlands, etc.).", true),
                        new DocumentItem("Age Evidence", "Must be 18–35 years of age at time of application.", true),
                        new DocumentItem("Financial Evidence", "At least AUD 5,000 in accessible funds.", true),
                        new DocumentItem("Return Ticket or Sufficient Funds", "Evidence of ability to purchase return ticket.", true),
                        new DocumentItem("Health Insurance Evidence", "Recommended; required for some nationalities.", false),
                        new DocumentItem("Health Examination Results", "May be required depending on planned activities (e.g., healthcare work).", false),
                        new DocumentItem("National Police Clearance", "Character declaration during application.", false)
                ),
                List.of(
                        "Must be a citizen of an eligible country",
                        "Between 18 and 35 years old",
                        "No dependent children accompanying you in Australia",
                        "Have not previously held a subclass 417 visa (first application)"
                ),
                List.of(
                        "Can extend to a second year (subclass 417) by completing 88 days of regional work",
                        "Can extend to a third year by completing an additional 179 days of regional work",
                        "May work for any single employer for up to 6 months"
                )
        ));

        list.add(new VisaCategory(
                "working-holiday-462", "Subclass 462", "Work and Holiday Visa", "Working Holiday",
                "&#127774;",
                "Similar to the 417, allows young people from additional eligible countries to work and holiday in Australia.",
                "Several days – 4 weeks", "12 months", "AUD 635",
                List.of(
                        new DocumentItem("Valid Passport", "From an eligible country (e.g., USA, Thailand, Vietnam, India, China, etc.).", true),
                        new DocumentItem("Age Evidence", "Must be 18–30 years of age.", true),
                        new DocumentItem("Financial Evidence", "At least AUD 5,000 in accessible funds.", true),
                        new DocumentItem("Functional English Evidence", "Demonstrate you can understand and communicate in English.", true),
                        new DocumentItem("Tertiary Qualification or Study Evidence", "Completed at least 2 years of tertiary study (some countries).", true),
                        new DocumentItem("Government Approval Letter (some countries)", "Letter of support from home country government for certain nationalities.", false),
                        new DocumentItem("Health Examination Results", "May be required.", false),
                        new DocumentItem("Health Insurance Evidence", "Recommended for the duration of stay.", false)
                ),
                List.of(
                        "Citizens of eligible countries (different list from 417)",
                        "Between 18 and 30 years old",
                        "Meet English language and tertiary study requirements (varies by country)",
                        "No dependent children in Australia"
                ),
                List.of(
                        "Can apply for a second 462 visa by completing 88 days of regional work",
                        "Work restrictions: no more than 6 months with a single employer",
                        "Different country-specific requirements apply — check the DOHA website"
                )
        ));

        // ===================== PERMANENT RESIDENCE PATHWAYS =====================
        list.add(new VisaCategory(
                "global-talent-858", "Subclass 858", "Global Talent Visa", "Permanent Residence",
                "&#11088;",
                "Permanent visa for highly distinguished individuals in target sectors who can benefit Australia.",
                "2–6 months", "Permanent", "AUD 4,640",
                List.of(
                        new DocumentItem("Valid Passport", "Current valid passport.", true),
                        new DocumentItem("Expression of Interest (EOI)", "Submit an EOI to the Global Talent Program.", true),
                        new DocumentItem("Nominator Letter", "A prominent Australian person in your field endorses your application.", true),
                        new DocumentItem("CV / Resume", "Comprehensive CV showing achievements, publications, awards.", true),
                        new DocumentItem("Evidence of International Recognition", "Awards, media coverage, publications, citations, keynote invitations.", true),
                        new DocumentItem("Income Evidence", "Evidence of earning above the Fair Work high income threshold (AUD 167,500+).", true),
                        new DocumentItem("Health Examination Results", "Full medical examination.", true),
                        new DocumentItem("National Police Clearance", "From all relevant countries.", true),
                        new DocumentItem("Reference Letters", "From prominent experts in your field.", false),
                        new DocumentItem("Business Registration / IP Evidence", "Patents, company ownership records if applicable.", false)
                ),
                List.of(
                        "Must work in one of the target sectors (tech, sciences, research, finance, sports, arts, etc.)",
                        "Must have international recognition in your field",
                        "Expected income to be above the high income threshold",
                        "Endorsed by a prominent Australian nominator"
                ),
                List.of(
                        "Fastest pathway to Australian PR for highly skilled individuals",
                        "No age restrictions",
                        "Family members can be included",
                        "Target sectors include AgTech, Space, FinTech, MedTech, CyberSecurity, and more"
                )
        ));

        list.add(new VisaCategory(
                "resident-return-155", "Subclass 155", "Resident Return Visa (RRV)", "Permanent Residence",
                "&#11088;",
                "Allows current or former Australian permanent residents to re-enter Australia after travelling overseas.",
                "2–6 weeks", "5 years travel facility", "AUD 415",
                List.of(
                        new DocumentItem("Valid Passport", "Current passport.", true),
                        new DocumentItem("Current or Former Australian PR Evidence", "PR visa label or ImmiCard.", true),
                        new DocumentItem("Evidence of Australian Residence", "Proof you have lived in Australia for 2 years in the last 5 years.", true),
                        new DocumentItem("Employment/Business Ties", "If you haven't lived 2 years in Australia, show substantial ties.", false),
                        new DocumentItem("Family Ties Evidence", "If claiming family ties as grounds for grant.", false)
                ),
                List.of(
                        "Must currently hold or have held an Australian permanent visa",
                        "Must have lived in Australia for 2 of the last 5 years, OR",
                        "Have substantial ties to Australia (employment, family, etc.)"
                ),
                List.of(
                        "Grants a 5-year travel facility attached to your existing PR",
                        "Does not grant a new permanent visa — it renews the travel entitlement",
                        "Apply before your current travel facility expires if possible"
                )
        ));

        return list;
    }
}
