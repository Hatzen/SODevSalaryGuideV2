import SurveyEntry from './surveyEntry'

export default class ResultSetForYear {
    resultSet: SurveyEntry[] = []
    overallEntryCount = 0
    invalidEntryCount = 0

    year = -1
}