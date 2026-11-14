<script lang="ts">
    type Props = {
        levels: Array<number>;
    }

    let { levels }: Props = $props()
    // todo: ovo ce trebat iz baze. za sad cisto hardcoded da se proba frontend.
    let thresholds = {
        kriticno: 2,
        nisko: 1
    }

    const bloodStates = (() => levels)().map(level => {
        if (level <= thresholds.nisko) {
            return "nisko"
        }
        else if (level <= thresholds.kriticno) {
            return "kriticno"
        }
        else {
            return "dovoljno"
        }
    })
</script>

<!-- todo: more ovo i bolje. -->
<div class="blood-type-grid">
    <div class="type-ab-positive" data-blood-state={bloodStates[0]}>AB+</div>
    <div class="type-ab-negative" data-blood-state={bloodStates[1]}>AB-</div>
    <div class="type-a-positive"  data-blood-state={bloodStates[2]}>A+</div>
    <div class="type-a-negative"  data-blood-state={bloodStates[3]}>A-</div>
    <div class="type-b-positive"  data-blood-state={bloodStates[4]}>B+</div>
    <div class="type-b-negative"  data-blood-state={bloodStates[5]}>B-</div>
    <div class="type-o-positive"  data-blood-state={bloodStates[6]}>O+</div>
    <div class="type-o-negative"  data-blood-state={bloodStates[7]}>O-</div>
</div>

<style>
    .blood-type-grid {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 1rem;
        margin-block-end: 2rem;
    }

    div[class^="type"] {
        height: 100px;
        border-radius: 12px;
        background-color: antiquewhite;

        &[data-blood-state="dovoljno"] {
            background-color: red;
        }

        &[data-blood-state="kriticno"] {
            background-color: tomato;
        }

        &[data-blood-state="nisko"] {
            background-color: coral;
        }
    }

    @media (min-width: 768px) {
        .blood-type-grid {
            grid-template-columns: repeat(4, 1fr);
        }
    }


</style>