import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ExampleClient from "../api/exampleClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class ExamplePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onCreate', 'renderExample'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.getElementById('subscription-form').addEventListener('submit', this.onCreate);
        this.client = new ExampleClient();

        this.dataStore.addChangeListener(this.renderExample)
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderExample() {
        let resultArea = document.getElementById("result-info");

        const example = this.dataStore.get("example");

        if (example) {
            resultArea.innerHTML = `
                <div>Name: ${example.name}</div>
                <div>UserId: ${example.userId}</div>
                <div>Days: ${example.daysOfWeek}</div>
                <div>Days: ${example.secondDayOfWeek}</div>
                <div>Pickup: ${example.pickupTime}</div>
                <div>Bins: ${example.numOfBins}</div>
            `
        } else {
        resultArea.innerHTML = "No Item";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("example", null);

        let result = await this.client.getExample(id, this.errorHandler);
        this.dataStore.set("example", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
            event.preventDefault();
            this.dataStore.set("example", null);

            let name = document.getElementById("name-field").value;
            let day = document.getElementById("day-field").value;
            let secondDay = document.getElementById("second-day-field").value;
            let time = document.getElementById("time-field").value;
            let bins = document.getElementById("create-bin-field").value;

            const createdExample = await this.client.createExample(name, day, secondDay, time, bins, this.errorHandler);
            this.dataStore.set("example", createdExample);

            if (createdExample) {
                this.showMessage(`Created ${createdExample.name}!`)
            } else {
                this.errorHandler("Error creating!  Try again...");
            }
            
    }

    async subscriptionUpdate(event) {
        event.preventDefault();
        this.dataStore.set("example", null);
 
        let name = document.getElementById("update-name-field").value;
        let day = document.getElementById("update-day-field").value;
        let time = document.getElementById("update-time-field").value;
        let bins = document.getElementById("update-bin-field").value;
 
        const createdExample = await this.client.createExample(name, day, time, bins, this.errorHandler);
        this.dataStore.set("example", createdExample);
 
        if (createdExample) {
        this.showMessage(`Created ${createdExample.name}!`)
            } else {
            this.errorHandler("Error creating!  Try again...");
            }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const examplePage = new ExamplePage();
    examplePage.mount();
};

window.addEventListener('DOMContentLoaded', main);