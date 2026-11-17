export const tlAppointmentStatus = (status: string) => {
    const stat = status.toUpperCase()

    if      (stat === "ARRIVED")    { return "PRISTUPIO" }
    else if (stat === "CANCELLED")  { return "OTKAZAN" }
    else if (stat === "DONATING")   { return "DARIVANJE U TIJEKU" }
    else if (stat === "FINISHED")   { return "USPJEŠNO DAROVANO" }
    else if (stat === "NOTARRIVED") { return "NIJE PRISTUPIO" }
    else if (stat === "REJECTED")   { return "ODBIJEN NA PREGLEDU" }
    else if (stat === "RESERVED")   { return "REZERVIRAN" }
    else if (stat === "SCREENING")  { return "NA PREGLEDU" }
    else { throw new Error("Predan string koji ne predstavlja jedan od statusa rezervacije") }
}