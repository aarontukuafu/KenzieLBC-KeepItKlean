import BaseClass from "../util/baseClass";
import axios from 'axios'


export default class ExampleClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getExample', 'createExample'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    /**
     * Gets the concert for the given ID.
     * @param id Unique identifier for a concert
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The concert
     */
    async getExample(id, errorCallback) {
        try {
            const response = await this.client.get(`/example/${id}`);
            return response.data;
        } catch (error) {
            this.handleError("getConcert", error, errorCallback)
        }
    }

    async createExample(name,day,secondDay,time,bins,errorCallback) {
//      Added secondDay
        try {
            const response = await this.client.post(`example`, {
                name: name,
                daysOfWeek: day,
                secondDayOfWeek: secondDay,
                pickupTime: time,
                numOfBins: bins
            });
            return response.data;
        } catch (error) {
            this.handleError("createExample", error, errorCallback);
        }
    }

    async createReview(name,review,errorCallback) {
                try {
                    const response = await this.client.post(`review`, {
                        name: name,
                        reviewByCustomer: review,
                    });
                    return response.data;
                } catch (error) {
                    this.handleError("createReview", error, errorCallback);
                }
            }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(method, error, errorCallback) {
        console.error(method + " failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + " failed - " + error);
        }
    }
// should create a new post
    async updateSubscription(name,day,secondDay,time,bins,errorCallback) {
        //      Added day, time, bins
                try {
                    const response = await this.client.post(`example`, {
                        name: name,
                        daysOfWeek: day,
                        secondDayOfWeek: secondDay,
                        pickupTime: time,
                        numOfBins: bins
                    });
                    return response.data;
                } catch (error) {
                    this.handleError("createExample", error, errorCallback);
                }
            }
}





