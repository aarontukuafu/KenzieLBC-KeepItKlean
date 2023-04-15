import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ExampleClient from "../api/exampleClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class ExamplePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onCreate', 'renderExample', 'renderReview', 'createReview', 'subscriptionUpdate'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.getElementById('subscription-form').addEventListener('submit', this.onCreate);
        document.getElementById('update-subscription-form').addEventListener('submit', this.subscriptionUpdate);
        document.getElementById('review-form').addEventListener('submit', this.createReview);
        this.client = new ExampleClient();

        this.dataStore.addChangeListener(this.renderExample);
        this.dataStore.addChangeListener(this.renderReview);
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderExample() {
        let resultArea = document.getElementById("result-info");

        const example = this.dataStore.get("example");

        if (example) {
            resultArea.innerHTML = `
                <div>Customer Name: ${example.name}</div>
                <div>Customer ID: ${example.userId}</div>
                <div>Day Of Pickup: ${example.daysOfWeek}</div>
                <div>Second Day Of Pickup (if applicable): ${example.secondDayOfWeek}</div>
                <div>Pickup Time: ${example.pickupTime}</div>
                <div>Number Of Bins: ${example.numOfBins}</div>
            `
        } else {
        resultArea.innerHTML = "No Item";
        }
    }

    async renderReview() {
        let resultArea = document.getElementById("review-info");

        const review = this.dataStore.get("review");

        if (review) {
            resultArea.innerHTML = `
                <div>Customer Name: ${review.name}</div>
                <div>Review: ${review.reviewByCustomer}</div>
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
                this.showMessage(`Created ${createdExample.name}!`);
                // window.location.href = 'http://localhost:5001/review.html/' + createdExample.userId;
            } else {
                this.errorHandler("Error creating!  Try again...");
            }
            
    }

    async createReview(event) {
        event.preventDefault();
        this.dataStore.set("review", null);

        let name = document.getElementById("reviewName-field").value;
        let review = document.getElementById("review-field").value;

        const createdReview = await this.client.createReview(name, review, this.errorHandler);
        this.dataStore.set("review", createdReview);

        if (createdReview) {
            this.showMessage(`Created ${createdReview.name}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
        
}

    async subscriptionUpdate(event) {
        event.preventDefault();
        console.log("test#0")
        this.dataStore.set("example", null);
        console.log("testing...")
 
        let name = document.getElementById("update-name-field").value;
        let id = document.getElementById("user-id-field").value;
        let day = document.getElementById("update-day-field").value;
        let secondDay = document.getElementById("update-second-day-field").value;
        let time = document.getElementById("update-time-field").value;
        let bins = document.getElementById("update-bin-field").value;
 
        const updateSubscription = await this.client.updateSubscription(name, id, day, secondDay, time, bins, this.errorHandler);
        this.dataStore.set("example", updateSubscription);
        console.log("testing...1")
 
        if (updateSubscription) {
        this.showMessage(`Created ${updateSubscription.name}!`)
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