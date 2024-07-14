import ResultSetForYear from '../model/resultSetForYear'
import ControlState from '../model/controlState'
import axios from 'axios';


// https://medium.com/november-five/using-apollo-client-with-a-rest-api-ad0203a807cd
export default class SurveyEntryService {
    get serverUrl(): string {
        // TODO: Configure somehow via springs config server or reverse proxy
        const isDebug = process.env.NODE_ENV === "development"
        if (isDebug) {
            return "http://localhost:8080"
        }
        return "http://ui-backend:8121"
    }

    
    public getSurveyEntries(filter: ControlState): Promise<ResultSetForYear[]> {
        return axios.post(this.serverUrl + "/api/v1/participations/filtered", filter)
    }

    
    public getFilterValues(): Promise<{countries: [string], abilities: [string], educations: [string]}> {
        return axios.get(this.serverUrl + "/api/v1/participations/values")
    }

}