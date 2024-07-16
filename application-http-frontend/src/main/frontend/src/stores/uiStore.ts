import { action, makeObservable, observable, observe } from 'mobx'
import SurveyEntry from '../model/surveyEntry'
import { ControlStore } from './controlStore'
import {  EntryStore } from './entryStore'

export class UiStore {

    private static readonly renderPeriodInMs = 3000
    private readonly controlStore: ControlStore
    private readonly entryStore: EntryStore
    
    private timeoutId!: number
    private dataChanged = true

    constructor(controlStore: ControlStore, entryStore: EntryStore) {
        this.controlStore = controlStore
        this.entryStore = entryStore
        
        // makeAutoObservable(this)
        this.initStore()

        this.resetRenderSchedule()
    }

    private initStore(): void {
        
        makeObservable(this, {
            udpateFilteredData: action,
        })

        // observable(this.filteredData)

        // observe(this.entryStore.parsedData.overallEntryCount, this.handleChanges.bind(this))
        // observe(this.entryStore.parsedDataByYear, 2011, this.handleChanges.bind(this))
        // observe(this.entryStore, 'parsedDataByYear', this.handleChanges) //.bind(this)
        // observe(this.controlStore, 'controlState', this.handleChanges)
        observe(this.entryStore.parsedDataByYear, this.handleChanges.bind(this))
        // observe(this.controlStore.controlState, this.handleChanges.bind(this))
        /*
        reaction(
            () => this.entryStore.parsedDataByYear,
            flag => {
                this.handleChanges()
            }
        )
        reaction(
            () => this.controlStore.controlState,
            flag => {
                this.handleChanges()
            }
        )
        */
        // observe(this.controlStore.controlState, this.handleChanges.bind(this))
    }

    private handleChanges(): void {
        //
        this.dataChanged = true
        this.resetRenderSchedule()
    }

    private resetRenderSchedule (): void {
        // TODO: get rid of schedule and use a simple button... or at least check for changes and 5s wait..
        // window.clearInterval(this.timeoutId)
        // this.timeoutId = window.setInterval(this.udpateFilteredData.bind(this), UiStore.renderPeriodInMs)
    }

    udpateFilteredData (): void {
        this.entryStore.loadData()
    }

}