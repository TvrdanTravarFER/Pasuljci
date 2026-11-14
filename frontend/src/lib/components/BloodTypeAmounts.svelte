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
        if (level <= thresholds.kriticno) {
            return "nisko"
        }
        else if (level <= thresholds.nisko) {
            return "kriticno"
        }
        else {
            return "dovoljno"
        }
    })
</script>

<!-- todo: more ovo i bolje. -->
<div class="blood-type-grid">
    <div class="type-ab-positive" data-blood-state={bloodStates[0]}>
        <span class="blood-type-badge">AB+</span>
        <span class="blood-type-state">{`(${bloodStates[0]})`}</span>
    </div>
    <div class="type-ab-negative" data-blood-state={bloodStates[1]}>
        <span class="blood-type-badge">AB-</span>
        <span class="blood-type-state">{`(${bloodStates[1]})`}</span>
    </div>
    <div class="type-a-positive"  data-blood-state={bloodStates[2]}>
        <span class="blood-type-badge">A+</span>
        <span class="blood-type-state">{`(${bloodStates[2]})`}</span>
    </div>
    <div class="type-a-negative"  data-blood-state={bloodStates[3]}>
        <span class="blood-type-badge">A-</span>
        <span class="blood-type-state">{`(${bloodStates[3]})`}</span>
    </div>
    <div class="type-b-positive"  data-blood-state={bloodStates[4]}>
        <span class="blood-type-badge">B+</span>
        <span class="blood-type-state">{`(${bloodStates[4]})`}</span>
    </div>
    <div class="type-b-negative"  data-blood-state={bloodStates[5]}>
        <span class="blood-type-badge">B-</span>
        <span class="blood-type-state">{`(${bloodStates[5]})`}</span>
    </div>
    <div class="type-o-positive"  data-blood-state={bloodStates[6]}>
        <span class="blood-type-badge">O+</span>
        <span class="blood-type-state">{`(${bloodStates[6]})`}</span>
    </div>
    <div class="type-o-negative"  data-blood-state={bloodStates[7]}>
        <span class="blood-type-badge">O-</span>
        <span class="blood-type-state">{`(${bloodStates[7]})`}</span>
    </div>
</div>

<style>
    .blood-type-grid {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 1rem;
        margin-block: 3rem;
    }

    div[class^="type"] {
        height: 100px;
        border-radius: 12px;
        background-color: antiquewhite;

        &[data-blood-state="dovoljno"] {
            background-color: var(--color-blood-sufficient);
        }

        &[data-blood-state="nisko"] {
            background-color: var(--color-blood-low);
        }

        &[data-blood-state="kriticno"] {
            background-color: var(--color-blood-critical);
        }
    }

    @media (min-width: 768px) {
        .blood-type-grid {
            grid-template-columns: repeat(4, 1fr);

            div {
                display: grid;
                place-items: center;
                padding-block: 1rem;
            }

            .blood-type-badge {
                display: grid;
                place-items: center;
                font-size: 1.75rem;
                font-weight: 700;
                color: #ffffff;
                margin-block-end: 0;
            }

            .blood-type-state {
                color: #ffffff;
            }
        }
    }
</style>