"use strict";
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result["default"] = mod;
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
const moment = __importStar(require("moment"));
console.log("Working with dates.....");
const now = new Date();
if (now instanceof Date && 5 > 7) {
    console.log("Date without Time", now.toDateString());
    console.log("Date Time", now.toTimeString());
    console.log("Locale String", now.toLocaleString());
    console.log("Locale Date String", now.toLocaleDateString());
    console.log("toISOString", now.toISOString().split("T")[0]);
}
// This is the result from Json.stringify
const dateDeserializeFromSpring = {
    year: 2019,
    month: "MAY",
    chronology: { calendarType: "iso8601", id: "ISO" },
    dayOfMonth: 1,
    dayOfWeek: "WEDNESDAY",
    era: "CE",
    dayOfYear: 121,
    leapYear: false,
    monthValue: 5
};
const strDateFromSpring = JSON.stringify(dateDeserializeFromSpring);
const myDate = JSON.parse(strDateFromSpring, ReviveDateTime);
function ReviveDateTime(key, value) {
    if (typeof value === "string") {
        let a = /\/Date\((\d*)\)\//.exec(value);
        if (a) {
            return new Date(+a[1]);
        }
    }
    return value;
}
console.log(myDate, typeof myDate);
console.log("Moment,", moment.parseZone(strDateFromSpring));
