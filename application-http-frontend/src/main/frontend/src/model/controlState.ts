import { Gender } from './gender'
import SurveyEntry from './surveyEntry'

export default class ControlState {
    selectedYears!: { [year: number]: boolean }
    expirienceInYears!: [min: number, max:number]
    genders!: Gender[]
    abilities!: string[]
    companySize: [min: number, max:number] = [1, 100000]
    countries: string[] = []
    degrees: string[] = []

    gendersFilterActive = false
    abilitiesFilterActive = false
    expirienceFilterActive = false
    
    companySizeFilterActive = false
    degreeFilterActive = false
    countriesFilterActive = false
    

    constructor (partial: Partial<ControlState>) {
        Object.assign(this, partial)
    }
}
