export const dowToNumber = (dow: string) => {
    if (dow === "Ponedjeljak") { return 1 }
    else if (dow === "Utorak") { return 2 }
    else if (dow === "Srijeda") { return 3 }
    else if (dow === "Četvrtak") { return 4 }
    else if (dow === "Petak") { return 5 }
    else if (dow === "Subota") { return 6 }
    else if (dow === "Nedjelja") { return 0 }
    else { throw new Error("Funkciji je predan string koji ne predstavlja niti jedan dan u tjednu") }
}

export const numberToDow = (num: number) => {
    const daysOfWeek = ["Nedjelja", "Ponedjeljak", "Utorak", "Srijeda", "Četvrtak", "Petak", "Subota"]

    if (num < 1 || num > 7) { throw new Error("Neispravan broj dana u tjednu predan funkciji (trebam: broj [0,6])") }

    return daysOfWeek[num]
}