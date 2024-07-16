import FreeCurrency from '../model/currencyValues'
import { makeAutoObservable } from 'mobx'
import StackOverflowCsvReader from '../services/stackOverflowCsvReader'
import { ParseStepResult } from 'papaparse'
import ResultSetForYear from '../model/resultSetForYear'
import { AVAILABLE_YEARS } from '../model/constantMetaData'
import ControlState from '../model/controlState'

// https://devlinduldulao.pro/mobx-in-a-nutshell/
export class EntryStore {
    
    parsedData: ResultSetForYear = new ResultSetForYear()
    parsedDataByYear: EntriesByYearMap = {
        2011: new ResultSetForYear(),
        2012: new ResultSetForYear(),
        2013: new ResultSetForYear(),
        2014: new ResultSetForYear(),
        2015: new ResultSetForYear(),
        2016: new ResultSetForYear(),
        2017: new ResultSetForYear(),
        2018: new ResultSetForYear(),
        2019: new ResultSetForYear(),
        2020: new ResultSetForYear(),
        2021: new ResultSetForYear(),
        2022: new ResultSetForYear()
    }

    currencyValues!: FreeCurrency
    reader!: StackOverflowCsvReader
    values?: {countries: [string], abilities: [string], educations: [string]}

    constructor() {
        makeAutoObservable(this)
        this.reader = new StackOverflowCsvReader()

        this.reader.getFilterValues().then(it => this.values = it.data)

        this.loadData()
    }

    /**
     * Actions
     */
    
    loadData (): void {
        const years = this.reader.getSurveyEntries(new ControlState({ selectedYears: { 2022: true } }))
        
        years.then(it => {
            it.data.forEach(year => {
                this.parsedDataByYear[year.year] = year
                this.parsedData.resultSet = this.parsedData.resultSet.concat(year.resultSet)
            })
        })

    }

}

export type EntriesByYearMap = { [year: number]: ResultSetForYear }

export default new EntryStore()