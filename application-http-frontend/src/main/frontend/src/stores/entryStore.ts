import FreeCurrency from '../model/currencyValues'
import { makeAutoObservable, runInAction } from 'mobx'
import StackOverflowCsvReader from '../services/stackOverflowCsvReader'
import { ParseStepResult } from 'papaparse'
import ResultSetForYear from '../model/resultSetForYear'
import { AVAILABLE_YEARS } from '../model/constantMetaData'
import ControlState from '../model/controlState'
import controlStore from './controlStore'

// https://devlinduldulao.pro/mobx-in-a-nutshell/
export class EntryStore {
    
    isLoading = false
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
        
        this.loadFilterValues()
        this.loadData()
    }

    /**
     * Actions
     */

    loadFilterValues(): void {
        this.reader.getFilterValues().then(it => runInAction(() => this.values = it.data))
    }
    
    loadData (): void {
        const years = this.reader.getSurveyEntries(controlStore.controlState)
        
        this.isLoading = true
        years.then(it => {
            runInAction(() => {
                it.data.forEach(year => {
                    this.parsedDataByYear[year.year] = year
                    this.parsedData.resultSet = this.parsedData.resultSet.concat(year.resultSet)
                })
              })
        })
        .finally(() => {
            runInAction(() => this.isLoading = false)
        })

    }

}

export type EntriesByYearMap = { [year: number]: ResultSetForYear }

export default new EntryStore()