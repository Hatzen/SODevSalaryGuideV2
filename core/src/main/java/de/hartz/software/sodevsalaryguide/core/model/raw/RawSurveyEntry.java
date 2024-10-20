package de.hartz.software.sodevsalaryguide.core.model.raw;

import java.util.List;

public interface RawSurveyEntry {
  // What Country or Region do you live in?,Which US State or Territory do you live in?,How old are
  // you?,How many years of IT/Programming experience do you have?,How would you best describe the
  // industry you currently work in?,How many people work for your company?,Which of the following
  // best describes your occupation?,"Including yourself, how many developers are employed at your
  // company?",How large is the team that you work on?,What other departments / roles do you
  // interact with regularly?,,,,,,,,,,"If your company has a native mobile app, what platforms do
  // you support?",,,,,,,"If you make a software product, how does your company make money? (You can
  // choose more than one)",,,,,,,,"In an average week, how do you spend your time?",,,,,,,,What is
  // your involvement in purchasing products or services for the company you work for? (You can
  // choose more than one),,,,,,,What types of purchases are you involved in?,,,,,,"What is your
  // budget for outside expenditures (hardware, software, consulting, etc) for 2013?",Which of the
  // following languages or technologies have you used significantly in the past
  // year?,,,,,,,,,,,,,,Which technologies are you excited about?,,,,,,,,,,,Which desktop operating
  // system do you use the most?,"Please rate how important each of the following characteristics of
  // a company/job offer are to you.    Please select a MAXIMUM of 3 items as ""Non-Negotiables"" to
  // help us identify the most important items, those where you would never consider a company if
  // they didn't meet them.",,,,,,,,,,,,,,,,Have you changed jobs in the last 12 months?,What best
  // describes your career / job satisfaction?,"Including bonus, what is your annual compensation in
  // USD?",Which technology products do you own? (You can choose more than one),,,,,,,,,,,,,,"In the
  // last 12 months, how much money have you spent on personal technology-related purchases?",Please
  // rate the advertising you've seen on Stack Overflow,,,,,,What advertisers do you remember seeing
  // on Stack Overflow?,What is your current Stack Overflow reputation?,How do you use Stack
  // Overflow?,,,
  List<HeaderMetaUntyped> getHeaders();

  RawDataset getDataset();
}
